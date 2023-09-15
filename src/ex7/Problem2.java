/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex7;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import model.Department;
import model.Employee;

/**
 *
 * @author alongkornvanzoh
 */
public class Problem2 {

    public static void main(String[] args) {
        List<Employee> empById = getAllEmployeeOrderById();
        List<Employee> empByDepartment = getAllEmployeeOrderByDepartmentId();
        printEmployeeOrderById(empById);
        printEmployeeOrderByDepartmentId(empByDepartment);
    }

    public static List<Employee> getAllEmployeeOrderById() {
        EntityManager em = Persistence.createEntityManagerFactory("Ex7PU").createEntityManager();
        List<Employee> employees = em.createQuery("SELECT e FROM Employee e ORDER BY e.employeeid").getResultList();
        em.close();
        return employees;
    }

    public static List<Employee> getAllEmployeeOrderByDepartmentId() {
        EntityManager em = Persistence.createEntityManagerFactory("Ex7PU").createEntityManager();
        List<Employee> employees = em.createQuery("SELECT e FROM Employee e ORDER BY e.departmentid, e.employeeid").getResultList();
        em.close();
        return employees;
    }

    public static void printEmployeeOrderById(List<Employee> employees) {
        System.out.println("All employee (by ID)");
        System.out.println("--------------------");
        for (Employee employee : employees) {
            Department department = employee.getDepartmentid();
            System.out.println("ID: " + employee.getEmployeeid());
            System.out.println("Name: " + employee.getName());
            System.out.println("Job: " + employee.getJob());
            System.out.println("Salary: " + employee.getSalary());
            System.out.println("Department: " + department.getName());
            System.out.println("--------------------");
        }
        System.out.println();
    }

    private static void printEmployeeOrderByDepartmentId(List<Employee> employees) {
        System.out.println("All employee (by Department)");
        System.out.println("---------------------------");
        int switcher = 0;
        for (Employee employee : employees) {
            Department department = employee.getDepartmentid();
            if(department.getDepartmentid() != switcher) {
                switcher = department.getDepartmentid();
                System.out.print("Department ID: " + department.getDepartmentid());
                System.out.println(" Department Name: " + department.getName());
                System.out.println("---------------------------");
            }
            System.out.println("ID: " + employee.getEmployeeid());
            System.out.println("Name: " + employee.getName());
            System.out.println("Job: " + employee.getJob());
            System.out.println("Salary: " + employee.getSalary());
            System.out.println("---------------------------");
        }
    }
}

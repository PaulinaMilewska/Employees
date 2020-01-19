package spring.hibernate;

import spring.hibernate.employee.EmployeeDao;
import spring.hibernate.employee.Employees;

import java.util.Date;
import java.util.List;


public class MainHibernate {
    public static void main(String[] args) {


        EmployeeDao employeeDao = new EmployeeDao();
        Employees employee = new Employees("Raz", "RAz", "Czy", "mnie slychac", 666, 18, new Date(), 1);
        employeeDao.saveEmployee(employee);
        List<Employees> employeesList = employeeDao.getEmployees();

        Employees employeeToUpdate = employeesList.get(0);
        employeeToUpdate.setSalary(123);

        employeeDao.updateEmployees(employeeToUpdate);
        employeesList.forEach(System.out::println);
    }
}

package spring.hibernate;

import spring.hibernate.employee.EmployeeController;
import spring.hibernate.employee.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

//@SpringBootApplication
public class MainHibernate {



    public static void main(String[] args) {

//        EntityManager entityManager = null;
//        entityManager.createQuery("select  from employees");

//        EntityManagerFactory entityManagerFactory =
//                Persistence.createEntityManagerFactory("employees");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("Adam");
        EmployeeController employeeController = new EmployeeController();
        Employee employee = new Employee();
//        employee.setId(0);
        employee.setAddress("Sloneczna");
        employee.setAge(18);
        employee.setBenefit(1);
        employee.setCity("Warsaw");
        employee.setEmail("gmail");
        employee.setFirstName("Adam");
        employee.setLastName("Nowak");
        employee.setSalary(4000);
        Date date = new Date(20-11-2000);
//        Employees employee1 = new Employees("Test", "Test", "Test", "Test", 1000, 18 , date, 1);
        System.out.println("Ola");
        employee.setStartJobDate(date);
//        employeeController.save(employee);


//        employeeDao.saveEmployee(employee);
//        employeeDao.saveEmployee(employee1);
//        List<Employees> employeesList = employeeDao.getEmployees();
//        ModelAndView employeesList = employeeController.viewemp();
        System.out.println("Ela");

//        Employees employeeToUpdate = employeesList.get(0);
//        employeeToUpdate.setSalary(9999);
//        employeeDao.updateEmployees(employeeToUpdate);
//employeeDao.deleteEmployees(employee);

        System.out.println(employee);
//        employeesList.forEach(System.out::println);
//        SpringApplication.run(SpringApp.class, args);
    }
}

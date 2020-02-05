package spring.hibernate;

import javafx.scene.effect.SepiaTone;

import org.springframework.format.Printer;
import spring.hibernate.car.Cars;
import spring.hibernate.employee.Employees;
import spring.hibernate.printer.PrinterDao;
import spring.hibernate.printer.Printers;

import java.time.Instant;
import java.util.*;

public class DataSource {

    public static boolean isDataBaseConnection = Boolean.FALSE;

    public static void main(String[] args) {
        supplyDatabase();
    }


    public static void supplyDatabase() {
//        HibernateDao hibernateDao = null;
        CarEmployeeDao carEmployeeDao = null;


        try {
            carEmployeeDao = new CarEmployeeDao();
            isDataBaseConnection = Boolean.TRUE;
        } catch (NullPointerException ex) {
            System.out.println("Brak połączenia z bazą danych");
            ex.getMessage();
        }

        Employees employees = new Employees();
        employees.setFirstName("Jan");
        employees.setLastName("Nowakowski");
        employees.setAge(51);
        employees.setBenefit(1);
        employees.setCity("Warszawa");
        employees.setSalary(7000);
        employees.setAddress("Nowa");
//        Date date = new Date();
//        date= Date.from(Instant.parse("2000-02-04"));
        employees.setStartJobDate(new Date());
        employees.setEmail("example@wp.com");
//
//        carEmployeeDao.save(employees);

        Cars cars = new Cars();
        cars.setEmployees(employees);
        cars.setModel("Ceed");
        cars.setName("Kia");
        cars.setRegistrationDate(new Date());
//OK
//        carEmployeeDao.save(cars);

        Set<Cars> carsSet = new HashSet<>();
        carsSet.add(cars);
        employees.setCars(carsSet);

        Printers printer = new Printers();
        List<Employees> employeesList = new ArrayList<>();
        employeesList.add(employees);
        printer.setEmployees(employeesList);
        printer.setProducer("HP");
        printer.setModel("100");
//        printer.setEmployees(employees);

        Set<Printers> printerSet = new HashSet<>();
        printerSet.add(printer);
        employees.setPrinters(printerSet);

        //TERAZ DOPISANE
        PrinterDao printerDao = new PrinterDao();
        printerDao.savePrinter(printer);

        //OK
//        carEmployeeDao.save(printer);





//        carEmployeeDao.update(employees);
    }


    public static List<Employees> getEmployeeList() {
        List<Employees> list = new ArrayList<>();
//        Employees employee1 = new Employees("Test", "Test", "Test", "Test", 1000, 18, new Date(), 1);
//        employee1.setId((long) 0);
//        Employees employee2 = new Employees("Test", "Test", "Test", "Test", 1000, 18, new Date(), 1);
//        employee2.setId((long) 1);
//        list.add(employee1);
//        list.add(employee2);
        return list;
    }
}

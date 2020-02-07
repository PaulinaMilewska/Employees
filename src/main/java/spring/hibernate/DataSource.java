package spring.hibernate;

import spring.hibernate.car.Cars;
import spring.hibernate.employee.Employees;
import spring.hibernate.printer.Printers;

import java.util.*;

public class DataSource {

    public static boolean isDataBaseConnection = Boolean.FALSE;

    public static void main(String[] args) {
        supplyDatabase();
    }


    public static void supplyDatabase() {
//        HibernateDao hibernateDao = null;
        ServiceDao serviceDao = null;


        try {
            serviceDao = new ServiceDao();
            isDataBaseConnection = Boolean.TRUE;
        } catch (NullPointerException ex) {
            System.out.println("Brak połączenia z bazą danych");
            ex.getMessage();
        }

        Employees employees = new Employees();
        employees.setFirstName("Aniela");
        employees.setLastName("Nowakowska");
        employees.setAge(77);
        employees.setBenefit(1);
        employees.setCity("Stara Milosna");
        employees.setSalary(3000);
        employees.setAddress("Twoja 1");
//        Date date = new Date();
//        date= Date.from(Instant.parse("2000-02-04"));
        employees.setStartJobDate(new Date());
        employees.setEmail("example@wp.com");

        serviceDao.save(employees);


        Cars cars = new Cars();
        cars.setEmployees(employees);
        cars.setModel("Ceed");
        cars.setName("Kia");
        cars.setRegistrationDate(new Date());

        serviceDao.save(cars);

        Printers printers = new Printers();
//        printers.setEmployeesSet();
        printers.setModel("MP3001");
        printers.setProducer("Ricoh");

        serviceDao.save(printers);


        Set<Cars> carsSet = new HashSet<>();
        carsSet.add(cars);

        employees.setCars(carsSet);

//        Printer printer = new Printer();
//        printer.setEmployees(employees);
//        printer.setProducer("HP");
//        printer.setModel("100");
//        printer.setEmployees(employees);

//        Set<Printer> printerSet = new HashSet<>();
//        printerSet.add(printer);

//        employees.setPrinters(printerSet);

//        carEmployeeDao.update(employees);
    }


    public static List<Employees> getEmployeeList() {
        List<Employees> list = new ArrayList<>();
        Employees employee1 = new Employees("Test", "Test", "Test", "Test", 1000, 18, new Date(), 1);
//        employee1.setId(3);
//        Employees employee2 = new Employees("Test", "Test", "Test", "Test", 1000, 18, new Date(), 1);
//        employee2.setId(4);
//        list.add(employee1);
//        list.add(employee2);
        return list;
    }
}

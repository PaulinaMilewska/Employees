package spring.hibernate;

import javafx.scene.effect.SepiaTone;

import org.springframework.format.Printer;
import spring.hibernate.car.Cars;
import spring.hibernate.employee.Employees;

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
        employees.setAge(19);
        employees.setBenefit(1);
        employees.setCity("Warszawa");
        employees.setSalary(9000);
        employees.setAddress("Złota");
//        Date date = new Date();
//        date= Date.from(Instant.parse("2000-02-04"));
        employees.setStartJobDate(new Date());
        employees.setEmail("crimson.kuba@gmail.com");

        carEmployeeDao.save(employees);

        Cars cars = new Cars();
        cars.setEmployees(employees);
        cars.setModel("126p");
        cars.setName("Fiat");
        cars.setRegistrationDate(new Date());

        carEmployeeDao.save(cars);

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

        carEmployeeDao.update(employees);
    }


    public static List<Employees> getEmployeeList(){
        List<Employees> list = new ArrayList<>();
        Employees employee1 = new Employees("Test", "Test", "Test", "Test", 1000, 18 , new Date(), 1);
        employee1.setId(0);
        Employees employee2 = new Employees("Test", "Test", "Test", "Test", 1000, 18 , new Date(), 1);
        employee2.setId(1);
        list.add(employee1);
        list.add(employee2);
        return list;
    }
}

package spring.hibernate;

import org.h2.jdbc.JdbcConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.hibernate.car.Car;
import spring.hibernate.car.CarRepository;
import spring.hibernate.car.CarService;
import spring.hibernate.employee.EmployeeService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DataSource {

    public DataSource() {
    }

    Connection db;



    {
        try {
            db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CONNECTIS", "postgres", "paulina13");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean connectionWithDB = true;


    private EmployeeService employeeServiceservice;

    @Autowired
    private CarService carService;

    public List<Car> getListCars() {
//        if (!db.isClosed()) {
//        if (1==1) {
//            System.out.println(db.isClosed());
        return carService.listAll();
//        }
//        else {
//            List<Car> cars = new ArrayList<>();
//            cars.add(new Car());
//            return cars;
//        }
    }

}

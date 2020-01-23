package spring.hibernate;

import spring.hibernate.car.Car;
import spring.hibernate.car.CarDao;
import spring.hibernate.employee.EmployeeDao;
import spring.hibernate.employee.Employees;

import java.util.Date;
import java.util.List;


public class MainHibernate {
    public static void main(String[] args) {


        EmployeeDao employeeDao = new EmployeeDao();
        Employees employee = new Employees("Adam", "Nowak", "Słoneczna", "Warsaw", 77, 18, new Date(), 2);
        employeeDao.saveEmployee(employee);
        List<Employees> employeesList = employeeDao.getEmployees();

        Employees employeeToUpdate = employeesList.get(0);
        employeeToUpdate.setSalary(123);

        employeeDao.updateEmployees(employeeToUpdate);
        employeesList.forEach(System.out::println);

        CarDao carDao = new CarDao();
        Car car = new Car("VW", "Polo", 22, new Date());
//        "VW", "Polo", 22, new Date()
//        car.setId((long) 1);
//        car.setBrand("VW");
//        car.setMaxSpeed(189);
//        car.setModel("Passat");
//        car.setRegistrationDate(new Date());
//        carDao.saveCar(car);
//        List<Car> carList = carDao.getCar();
//
//        Car carToUpdate = carList.get(0);
////        carToUpdate.setSalary(123);
//
//        carDao.updateCar(carToUpdate);
//        carList.forEach(System.out::println);
    }
}
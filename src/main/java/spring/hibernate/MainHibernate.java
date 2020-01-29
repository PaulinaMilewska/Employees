package spring.hibernate;


import spring.hibernate.car.CarDao;
import spring.hibernate.car.Cars;
import spring.hibernate.employee.EmployeeDao;
import spring.hibernate.employee.Employees;
import spring.hibernate.printer.PrinterDao;
import spring.hibernate.printer.Printers;

import java.util.Date;
import java.util.List;


public class MainHibernate {
    public static void main(String[] args) {


        EmployeeDao employeeDao = new EmployeeDao();
        Employees employee = new Employees("Ewa", "Nowak", "Słoneczna", "Gdansk", 120, 18, new Date(), 2);
//        employeeDao.saveEmployee(employee);
        List<Employees> employeesList = employeeDao.getEmployees();

        //  Employees employeeToUpdate = employeesList.get(0);
        //    employeeToUpdate.setSalary(123);
//        employeeDao.updateEmployees(employeeToUpdate);
        employeesList.forEach(System.out::println);

        CarDao carDao = new CarDao();
        Cars car = new Cars(2, employee, "Polo", "VW", new Date());
        car.setId(100);
        carDao.saveCar(car);
        List<Cars> carList = carDao.getCar();

        Cars carToUpdate = carList.get(0);
//        carToUpdate.setSalary(123);

        carDao.updateCar(carToUpdate);
        carList.forEach(System.out::println);
    }

    PrinterDao printerDao = new PrinterDao();
}

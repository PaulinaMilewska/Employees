package spring.hibernate.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.hibernate.CarEmployeeDao;
import spring.hibernate.DataSource;
import spring.hibernate.TypeObject;


import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    private List<Employees> employees;
    private List<CarEmployeeDao> listCarEmpDao;
    @Autowired
    private EmployeeDao employeeDao;
    private CarEmployeeDao carEmployeeDao;



    public EmployeeController() {

        try {
//            carEmployeeDao = new CarEmployeeDao();
            employeeDao = new EmployeeDao();

            DataSource.supplyDatabase();
//            employees = carEmployeeDao.get(Employees.class);
            employees = employeeDao.getEmployees();
        } catch (
                NullPointerException exception) {
            exception.getMessage();
            employees = new ArrayList<>();

        }
    }

    @RequestMapping("/")
    public String indexGet() {
        return "index";
    }


    @RequestMapping(value = "/employeeform", method = RequestMethod.GET)
    public String showform(Model model) {
        model.addAttribute("employee", new Employees());
        return "employees/employeeform";
    }


    @RequestMapping("/save_employee")
    public ModelAndView saveEmployee(@ModelAttribute(value = "employee") Employees employee) {
        System.out.println();
        if (employee.getId() == 0) {
//            employeeDao.saveEmployee(employee);
            System.out.println("Add new employee");
            if (DataSource.isDataBaseConnection) {
//                carEmployeeDao.save(employee);
                employeeDao.saveEmployee(employee);
            }
//            employee.setId((long) employees.size());
            employee.setId((long) 1);
            employees.add(employee);


        } else {
            employeeDao.updateEmployees(employee);
//            updateEmployeeInList(employee);
            if (DataSource.isDataBaseConnection) {
//                carEmployeeDao.update(employee);
//                carEmployeeDao.get(employee.getId());
                employeeDao.getEmployeesById(Math.toIntExact(employee.getId()));
            }
//            employee.setId(employees.size());
            employees.set(Math.toIntExact(employee.getId() - 1), employee);
        }
        return new ModelAndView("redirect:/viewemployees");
    }

// doesn't edit last position
    @RequestMapping(value = "/edit_employee")
    public ModelAndView edit(@RequestParam(value = "employee_id") String employee_id) {
        System.out.printf("");
        Employees employee = getEmployeesById(Integer.parseInt(employee_id));
//        if (DataSource.isDataBaseConnection) {
//            carEmployeeDao.update(employee);
//        }
        return new ModelAndView("employees/employeeform", "employee", employee);
    }

    private Employees getEmployeesById(@RequestParam int employee_id) {
        return employees.stream().filter(f -> f.getId() == employee_id).findFirst().get();
    }

// doesn't delete last position
    @RequestMapping(value = "/delete_employee", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "employee_id") String employee_id) {
        Employees employeeToDelete = getEmployeesById(Integer.parseInt(employee_id));
        employees.remove(employeeToDelete);
        if (DataSource.isDataBaseConnection) {
//            carEmployeeDao.delete(employeeToDelete);
            employeeDao.deleteEmployee(employeeToDelete);
        }
        return new ModelAndView("redirect:/viewemployees");
    }


    @RequestMapping("/viewemployees")
    public ModelAndView viewemployees(Model model) {
//        List<Employees> list = employeeDao.getEmployees();
//        List<Employees> list = carEmployeeDao.get(Employees.class);
        return new ModelAndView("employees/viewemployees", "list", employees);
    }

    private void updateEmployeeInList(Employees employees) {
        Employees employeesTemp = getEmployeesById(Math.toIntExact(employees.getId()));
        employeesTemp.setFirstName(employees.getFirstName());
        employeesTemp.setLastName(employees.getLastName());
        employeesTemp.setAddress(employees.getAddress());
        employeesTemp.setCity(employees.getCity());
        employeesTemp.setSalary(employees.getSalary());
        employeesTemp.setAge(employees.getAge());
        employeesTemp.setStartJobDate(employees.getStartJobDate());
        employeesTemp.setBenefit(employees.getBenefit());
        employeesTemp.setCars(employees.getCars());
        employeesTemp.setPrinters(employees.getPrinters());
//        employeesTemp.setPhones(employees.getPhones());
    }

}


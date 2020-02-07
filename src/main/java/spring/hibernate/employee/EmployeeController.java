package spring.hibernate.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.hibernate.ServiceDao;
import spring.hibernate.DataSource;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController
//        extends ServiceDao
{
    @Autowired
    private EmployeeDao employeeDao;

    private List<Employees> list = new ArrayList<>();
//    private ServiceDao serviceDao;

    public EmployeeController() {


//        try {
//            serviceDao = new ServiceDao();
//            DataSource.supplyDatabase();
//            employeesList = serviceDao.get(Employees.class);
//        } catch (
//                NullPointerException exception) {
//            exception.getMessage();
//            employeesList = new ArrayList<>();
//        }
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

            System.out.println("Add new employee");
            if (DataSource.isDataBaseConnection) {
//                serviceDao.save(employee);
                employeeDao.saveEmployee(employee);

            }
//            employee.setId(employeesList.size());
            employee.setId(1);
            list.add(employee);

        } else {
//            employeeDao.updateEmployees(employee);
//            updateEmployeeInList(employee);
            if (DataSource.isDataBaseConnection) {
//                serviceDao.update(employee);
                employeeDao.updateEmployees(employee);
            }

            list.set(employee.getId() - 1, employee);
        }
        return new ModelAndView("redirect:/viewemployees");
    }

    // doesn't work
    @RequestMapping(value = "/edit_employee")
    public ModelAndView edit(@RequestParam(value = "employee_id") String employee_id) {
        System.out.printf("get employee with id:",employee_id);
        Employees employee = getEmployeesById(Integer.parseInt(employee_id));
//        if (DataSource.isDataBaseConnection) {
//            carEmployeeDao.update(employee);
//        }
        return new ModelAndView("employees/employeeform", "employee", employee);
    }

    private Employees getEmployeesById(@RequestParam int employee_id) {
        return list.stream().filter(f -> f.getId() == employee_id).findFirst().get();
    }


    @RequestMapping(value = "/delete_employee", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "employee_id") String employee_id) {
        Employees employeeToDelete = getEmployeesById(Integer.parseInt(employee_id));
        list.remove(employeeToDelete);
        if (DataSource.isDataBaseConnection) {
//            serviceDao.delete(employeeToDelete);
            employeeDao.deleteEmployee(employeeToDelete);
        }

        return new ModelAndView("redirect:/viewemployees");
    }


    @RequestMapping("/viewemployees")
    public ModelAndView viewemployees(Model model) {
        list= employeeDao.getEmployees();
        return new ModelAndView("employees/viewemployees", "list", list);
    }

//    private void updateEmployeeInList(Employees employees) {
//        Employees employeesTemp = getEmployeesById(employees.getId());
//        employeesTemp.setFirstName(employees.getFirstName());
//        employeesTemp.setLastName(employees.getLastName());
//        employeesTemp.setAddress(employees.getAddress());
//        employeesTemp.setCity(employees.getCity());
//        employeesTemp.setSalary(employees.getSalary());
//        employeesTemp.setAge(employees.getAge());
//        employeesTemp.setStartJobDate(employees.getStartJobDate());
//        employeesTemp.setBenefit(employees.getBenefit());
//        employeesTemp.setCars(employees.getCars());
//        employeesTemp.setPhones(employees.getPhones());
//    }

}


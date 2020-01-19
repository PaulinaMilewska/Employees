package spring.hibernate.employee;

import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    private List<Employees> list;

    private EmployeeDao employeeDao;


    public EmployeeController() {
        employeeDao = new EmployeeDao();
        try {
            list = employeeDao.getEmployees();
        } catch (
                NullPointerException exception) {
            System.out.println("No connection with database");
            exception.getMessage();
            list = new ArrayList<>();
        }
    }

    @RequestMapping("/")
    public String indexGet() {
        return "employees/index_emp";
    }

    @RequestMapping(value = "/employeeform", method = RequestMethod.GET)
    public String showform(Model model) {
        model.addAttribute("employee", new Employees());
        return "employees/employeeform";
    }

    @RequestMapping("/save_employee")
    public ModelAndView saveEmployee(@ModelAttribute(value = "employee") Employees employee) {
        if (employee.getId() == 0) {
            EmployeeDao employeeDao = new EmployeeDao();
            employeeDao.saveEmployee(employee);

        } else {
            employeeDao.updateEmployees(employee);
        }
        return new ModelAndView("redirect:/viewemployees");
    }

    @PostMapping(value = "/edit_employee")
    public ModelAndView edit(@RequestParam(value = "employee_id") String employee_id) {
        Employees employee = getEmployeesById(Integer.parseInt(employee_id));
        return new ModelAndView("employees/employeeform", "employee", employee);
    }


    private Employees getEmployeesById(@RequestParam int employee_id) {
        return list.stream().filter(f -> f.getId() == employee_id).findFirst().get();
    }

    @RequestMapping(value = "/delete_employee", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "employee_id") String employee_id) {
        Employees employeeToDelete = employeeDao.getEmployeesById(Integer.parseInt(employee_id));
        employeeDao.deleteEmployee(employeeToDelete);
        return new ModelAndView("redirect:/viewemployees");
    }

    @RequestMapping("/viewemployees")
    public ModelAndView viewemployees(Model model) {
        List<Employees> list = employeeDao.getEmployees();
        return new ModelAndView("employees/viewemployees", "list", list);
    }

}


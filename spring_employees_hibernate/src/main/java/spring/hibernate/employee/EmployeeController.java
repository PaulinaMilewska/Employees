package spring.hibernate.employee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
            Employees emp1 = new Employees("Rafael", "Michael", "123, Tutti St", "Roma", 5000, 34, new Date(2001 - 01 - 01), 1);
            Employees emp2 = new Employees("Pizza", "Pasta", "543, Allora St", "Milan", 2300, 52, new Date(2012 - 11 - 04), 0);
            list.addAll(Arrays.asList(emp1, emp2));
        }
    }

    @RequestMapping("/")
    public String indexGet() {
        return "index";
    }

    //seems to be working
    @RequestMapping(value = "/employeeform", method = RequestMethod.GET)
    public String showform(Model model) {
        model.addAttribute("employee", new Employees());
        return "employees/employeeform";
    }

    // not working
    @RequestMapping("/save_employee")
    public ModelAndView saveEmployee(@ModelAttribute(value = "employee") Employees employee) {
        if (employee.getId() == 0) {
            employeeDao.saveEmployee(employee);

        } else {
            employeeDao.updateEmployees(employee);
        }
        return new ModelAndView("redirect:/viewemployees");
    }

    // employee form works
    @RequestMapping(value = "/edit_employee")
    public ModelAndView edit(@RequestParam(value = "employee_id") String employee_id) {
        Employees employee = getEmployeesById(Integer.parseInt(employee_id));
        return new ModelAndView("employees/employeeform", "employee", employee);
    }



    private Employees getEmployeesById(@RequestParam int employee_id) {
        return list.stream().filter(f -> f.getId() == employee_id).findFirst().get();
    }

    // works only with test, not with database
    @RequestMapping(value = "/delete_employee", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "employee_id") String employee_id) {
        Employees employeeToDelete = employeeDao.getEmployeesById(Integer.parseInt(employee_id));
        employeeDao.deleteEmployee(employeeToDelete);
        return new ModelAndView("redirect:/viewemployees");
    }

    //works with database
    @RequestMapping("/viewemployees")
    public ModelAndView viewemployees(Model model) {
        List<Employees> list = employeeDao.getEmployees();
        return new ModelAndView("employees/viewemployees", "list", list);
    }

}


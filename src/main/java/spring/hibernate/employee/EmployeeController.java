package spring.hibernate.employee;

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
public class EmployeeController extends CarEmployeeDao{

    private List<Employees> list;
    private List<CarEmployeeDao> listCarEmpDao;

    private EmployeeDao employeeDao;
    private CarEmployeeDao carEmployeeDao;



    public EmployeeController() {

        try {
            carEmployeeDao = new CarEmployeeDao();
            DataSource.supplyDatabase();
            list = carEmployeeDao.get(Employees.class);
        } catch (
                NullPointerException exception) {
            exception.getMessage();
            list = new ArrayList<>();

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
        if (employee.getId() == 0) {
//            employeeDao.saveEmployee(employee);
            carEmployeeDao.save(employee);

        } else {
//            employeeDao.updateEmployees(employee);
            carEmployeeDao.update(employee);
        }
        return new ModelAndView("redirect:/viewemployees");
    }


    @RequestMapping(value = "/edit_employee")
    public ModelAndView edit(@RequestParam(value = "employee_id") String employee_id) {
        Employees employee = getEmployeesById(Integer.parseInt(employee_id));
        return new ModelAndView("employees/employeeform", "employee", employee);
    }

    private Employees getEmployeesById(@RequestParam int employee_id) {
        return list.stream().filter(f -> f.getId() == employee_id).findFirst().get();
    }


    @RequestMapping(value = "/delete_employee", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "employee_id") String employee_id) {
        Employees employeeToDelete = getEmployeesById(Integer.parseInt(employee_id));
        carEmployeeDao.delete(employeeToDelete);
        return new ModelAndView("redirect:/viewemployees");
    }


    @RequestMapping("/viewemployees")
    public ModelAndView viewemployees(Model model) {
//        List<Employees> list = employeeDao.getEmployees();
        List<Employees> list = carEmployeeDao.get(Employees.class);
        return new ModelAndView("employees/viewemployees", "list", list);
    }



}


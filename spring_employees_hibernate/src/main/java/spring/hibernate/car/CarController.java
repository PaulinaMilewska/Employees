package spring.hibernate.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.hibernate.DataSource;
import spring.hibernate.employee.Employee;

import java.sql.SQLException;
import java.util.List;

@Controller
public class CarController {
    public CarController() {

    }

    @Autowired
    private CarService service;
    private DataSource dataSource  = new DataSource();

    @RequestMapping(value = "/carform")
    public String showform(Model model) {
        model.addAttribute("car", new Car());
        return "emp/carform";
    }

    @RequestMapping(value = "/save_car", method = RequestMethod.POST)
//    public ModelAndView save(@ModelAttribute(value = "employees") Employees employees) {
    public String save(@ModelAttribute(value = "car") Car car) {
//        EmployeeController employeeController = new EmployeeController();
//      Car  car = new Car();
        System.out.println("Add new car");
        Long size = Long.valueOf(service.listAll().size());
        car.setId(size + 1);
        service.save(car);
        System.out.println("adddddd");
        return "redirect:/viewcar";
    }

    @RequestMapping("/viewcar")
    public String viewemp(Model model) throws SQLException {
        System.out.println("view");
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            String hql = "FROM Employees";
        List<Car> list = service.listAll();


//            List<Car> list = dataSource.getListCars();
            model.addAttribute("list", list);



//            List<Employees> list = session.createQuery(hql).list();
//            return session.createQuery(hql).list();


        return "emp/viewcar";
//        }
    }

}

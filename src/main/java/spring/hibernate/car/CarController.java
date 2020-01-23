package spring.hibernate.car;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.hibernate.employee.EmployeeDao;
import spring.hibernate.employee.Employees;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {
    private List<Car> list;
    private CarDao carDao;


    public CarController() {
        carDao = new CarDao();
        try {
            list = carDao.getCar();
        } catch (
                NullPointerException exception) {
            System.out.println("No connection with database");
            exception.getMessage();
            list = new ArrayList<>();


//            Employees emp1 = new Employees("Rafael", "Michael", "123, Tutti St", "Roma", 5000, 34, new Date(2001 - 01 - 01), 1);
//            Employees emp2 = new Employees("Pizza", "Pasta", "543, Allora St", "Milan", 2300, 52, new Date(2012 - 11 - 04), 0);
//            list.addAll(Arrays.asList(emp1, emp2));
        }
    }


    //seems to be working
    @RequestMapping(value = "/carform", method = RequestMethod.GET)
    public String showform(Model model) {
        model.addAttribute("car", new Car());
        return "car/carform";
    }

    // not working
    @RequestMapping("/save_car")
    public ModelAndView saveCar(@ModelAttribute(value = "car") Car car) {
        if (car.getId() == 0) {
            car.setId(Long.valueOf(list.size()+1));
            carDao.saveCar(car);
        } else {
            carDao.updateCar(car);
        }
        return new ModelAndView("redirect:/viewcar");
    }

    // employee form works
    @RequestMapping(value = "/edit_car")
    public ModelAndView edit(@RequestParam(value = "car_id") String car_id) {
        Car car = getCarById(Integer.parseInt(car_id));
        return new ModelAndView("car/carform", "car", car);
    }



    private Car getCarById(@RequestParam int car_id) {
        return list.stream().filter(f -> f.getId() == car_id).findFirst().get();
    }

    // works only with test, not with database
    @RequestMapping(value = "/delete_car", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "car_id") String car_id) {
        Car carToDelete = carDao.getCarById(Integer.parseInt(car_id));
        carDao.deleteCar(carToDelete);
        return new ModelAndView("redirect:/viewcar");
    }

    //works with database
    @RequestMapping("/viewcar")
    public ModelAndView viewcar(Model model) {
        List<Car> list = carDao.getCar();
        return new ModelAndView("car/viewcar", "list", list);
    }

}

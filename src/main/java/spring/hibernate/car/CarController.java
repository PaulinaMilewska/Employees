package spring.hibernate.car;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.hibernate.CarEmployeeDao;
import spring.hibernate.TypeObject;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {
    private List<Cars> list;
    private CarDao carDao;
    CarEmployeeDao carEmployeeDao;


    public CarController() {

        try {
            carEmployeeDao = new CarEmployeeDao();
//            DataSource.supplyDatabase();
            list = carEmployeeDao.get(Cars.class);
        } catch (
                NullPointerException exception) {
            exception.getMessage();
            list = new ArrayList<>();


        }
    }


    //seems to be working
    @RequestMapping(value = "/carform", method = RequestMethod.GET)
    public String showform(Model model) {
        model.addAttribute("car", new Cars());
        return "car/carform";
    }

    @RequestMapping("/save_car")
    public ModelAndView saveCar(@ModelAttribute(value = "car") Cars car) {
        if (car.getId() == 0) {
//            car.setId(Long.valueOf(list.size()+1));
            carEmployeeDao.save(car);
        } else {
            System.out.println(car.getId());
            carEmployeeDao.update(car);
            car.setId(car.getId());
        }
        return new ModelAndView("redirect:/viewcar");
    }

    // does't save changes - exception null ID
    @RequestMapping(value = "/edit_car")
    public ModelAndView edit(@RequestParam(value = "car_id") String car_id) {
        Cars car = getCarById(Integer.parseInt(car_id));
        return new ModelAndView("car/carform", "car", car);
    }


    private Cars getCarById(@RequestParam int car_id) {
        return list.stream().filter(f -> f.getId() == car_id).findFirst().get();
    }

// doesn't delete last position
    @RequestMapping(value = "/delete_car", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "car_id") String car_id) {
        Cars carToDelete =  getCarById(Integer.parseInt(car_id));
        carEmployeeDao.delete( carToDelete);
        return new ModelAndView("redirect:/viewcar");
    }


    @RequestMapping("/viewcar")
    public ModelAndView viewcar(Model model) {
//        List<Car> list = carDao.getCar();
        List<Cars> list = carEmployeeDao.get(Cars.class);
        return new ModelAndView("car/viewcar", "list", list);
    }

}

package spring.hibernate.car;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.hibernate.ServiceDao;
import spring.hibernate.DataSource;
import spring.hibernate.employee.Employees;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController  {
    private List<Cars> list;
    private CarDao carDao;
    ServiceDao serviceDao;


    public CarController()  {

        try {
            serviceDao = new ServiceDao();
//            DataSource.supplyDatabase();
            list = serviceDao.get(Cars.class);
        } catch (
                NullPointerException exception) {
            exception.getMessage();
            list = new ArrayList<>();
        }
    }


    @RequestMapping(value = "/carform", method = RequestMethod.GET)
    public String showform(Model model) {
        model.addAttribute("car", new Cars());
        return "car/carform";
    }

    @RequestMapping("/save_car")
    public ModelAndView saveCar(@ModelAttribute(value = "car") Cars car) {
        if (car.getId() == 0) {
            if (DataSource.isDataBaseConnection) {
                serviceDao.save(car);
            }
            car.setId(list.size());
            list.add(car);

        } else {
            System.out.println();
            System.out.println("Car ID -------- "+car.getId());
//            System.out.println("Employee ID -------- "+car.getEmployees().getId());
//            System.out.println("EMP__NAME: "+ car.getEmployees().getFirstName());
            if (DataSource.isDataBaseConnection) {
                car.setEmployees(emp);
                serviceDao.update(car);

            }
            list.set(car.getId()-1, car);
            updateCarInList(car);
        }
        return new ModelAndView("redirect:/viewcar");
    }
    Employees emp;


    // doesn't edit last position
    @RequestMapping(value = "/edit_car")
    public ModelAndView edit(@RequestParam(value = "car_id") String car_id) {
        System.out.println();
        Cars car = getCarById(Integer.parseInt(car_id));
        System.out.println("Car_id: "+ car_id);
        System.out.println("EMP_ID: "+ car.getEmployees().getId());
        System.out.println("EMP__NAME: "+ car.getEmployees().getFirstName());
        emp = car.getEmployees();
//        if (DataSource.isDataBaseConnection) {
//            carEmployeeDao.update(car);
//        }
            return new ModelAndView("car/carform", "car", car);
    }


    private Cars getCarById(@RequestParam int car_id) {
        return list.stream().filter(f -> f.getId() == car_id).findFirst().get();
    }

// doesn't delete last position
    @RequestMapping(value = "/delete_car", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "car_id") String car_id) {
        System.out.println("Car1 id: "+ car_id);
        Cars carToDelete =  getCarById(Integer.parseInt(car_id));
        System.out.println("Car2 id: "+ car_id);
        if (DataSource.isDataBaseConnection) {
            serviceDao.delete(carToDelete);
        }
        list.remove(carToDelete);
        return new ModelAndView("redirect:/viewcar");
    }


    @RequestMapping("/viewcar")
    public ModelAndView viewcar(Model model) {
//        List<Car> list = carDao.getCar();
        List<Cars> list = serviceDao.get(Cars.class);
        return new ModelAndView("car/viewcar", "list", list);
    }

    private void updateCarInList(Cars cars) {
        Cars carTemp = getCarById(cars.getId());
        carTemp.setName(cars.getName());
        carTemp.setModel(cars.getModel());
        carTemp.setRegistrationDate(cars.getRegistrationDate());
//        carTemp.setEmployees(cars.getEmployees());
    }

}

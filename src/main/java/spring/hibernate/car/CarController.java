package spring.hibernate.car;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.hibernate.ServiceDao;
import spring.hibernate.DataSource;
import spring.hibernate.employee.EmployeeDao;
import spring.hibernate.employee.Employees;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {
    private List<Cars> list = new ArrayList<>();
    private List<Employees> listEmp = new ArrayList<>();
    //    private CarServiceImpl carService;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private CarDao carDao;
//        ServiceDao serviceDao;

//    @Autowired
//    CarRepository repository;


    public CarController(CarDao carDao, EmployeeDao employeeDao) {
//    public CarController() {
        this.carDao = carDao;
        this.employeeDao = employeeDao;

        listEmp = employeeDao.getEmployees();
//        this.carService = carService;
//        list = carService.getAll();


//        try {
////            serviceDao = new ServiceDao();
//            carDao = new CarDao();
////            DataSource.supplyDatabase();
////            list = serviceDao.get(Cars.class);
//            list = carDao.getCars();
//        } catch (
//                NullPointerException exception) {
//            exception.getMessage();
//            list = new ArrayList<>();
//        }
    }


    @RequestMapping(value = "/carform", method = RequestMethod.GET)
    public String showform(Model model) {
        listEmp = employeeDao.getEmployees();
        model.addAttribute("car", new Cars());
        model.addAttribute("listEmp", listEmp);
        return "car/carform";
    }

    @RequestMapping("/save_car")
    public ModelAndView saveCar(@ModelAttribute(value = "car") Cars car,
                                @ModelAttribute(value = "emp.id") String emp_id_Sting) {
        int emp_id = Integer.parseInt(emp_id_Sting);
//        listEmp = employeeDao.getEmployees();
        Employees employeeToSet = listEmp.stream().filter(f -> f.getId() == emp_id).findFirst().get();

//        car.setEmployees(listEmp.get(emp_id));
        car.setEmployees(employeeToSet);
        System.out.println(car.getId()+ "   CAAAAARRRRR IIIDDDDDD");
        if (car.getId() == 0) {
            if (DataSource.isDataBaseConnection) {
                System.out.println("11111111112222222222222222222");
//                serviceDao.save(car);
                System.out.println("car id = 0 --- car id = 0 --- car id = 0 --- ");
                car.setId(list.size()+1);
                System.out.println("dont aaaaaaaaaadddddddddddddddddd");
                carDao.saveCar(car);
            }
            car.setId(list.size());
//            car.setId(list.size());
//            car.setEmployees(listEmp.get(0));
            System.out.println("AAAAAAAAAAAADDDDDDDDDDDDDDDDDD");
            list.add(car);

        } else {
            System.out.println();
            System.out.println("Car ID -------- " + car.getId());
//            System.out.println("Employee ID -------- "+car.getEmployees().getId());
//            System.out.println("EMP__NAME: "+ car.getEmployees().getFirstName());
            if (DataSource.isDataBaseConnection) {
                listEmp = employeeDao.getEmployees();
                int index = car.getId();
                car.setEmployees(listEmp.get(index));
//               serviceDao.update(car);
                carDao.updateCar(car);

            }
            list.set(car.getId() - 1, car);
            updateCarInList(car);
        }
        return new ModelAndView("redirect:/viewcar");
    }

    Employees emp;


    // doesn't edit last position
    @RequestMapping(value = "/edit_car")
    public ModelAndView edit(@RequestParam(value = "car_id") String car_id) {
        System.out.println();
        Long carId = Long.parseLong(car_id);
        Cars car = getCarById(carId);
        System.out.println("Car_id: " + car_id);
        System.out.println("EMP_ID: " + car.getEmployees().getId());
        System.out.println("EMP__NAME: " + car.getEmployees().getFirstName());
        emp = car.getEmployees();
//        if (DataSource.isDataBaseConnection) {
//            carEmployeeDao.update(car);
//        }
        return new ModelAndView("car/carform", "car", car);
    }


    private Cars getCarById(@RequestParam Long car_id) {
        return list.stream().filter(f -> f.getId() == car_id).findFirst().get();
    }
//    private Employees getEmpById(@RequestParam int car_id) {
//        return list.stream().filter(f -> f.getId() == car_id).findFirst().get();
//    }

    // doesn't delete last position
    @RequestMapping(value = "/delete_car", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "car_id") String car_id) {
        System.out.println("Car1 id: " + car_id);
        Long carId = Long.parseLong(car_id);
        Cars carToDelete = getCarById(carId);
        System.out.println("Car2 id: " + car_id);
        if (DataSource.isDataBaseConnection) {
//            serviceDao.delete(carToDelete);
            carDao.deleteCar(carToDelete);
        }
        list.remove(carToDelete);
        return new ModelAndView("redirect:/viewcar");
    }


    @RequestMapping("/viewcar")
    public ModelAndView viewcar(Model model) {
        System.out.println("________________________________________");
//        List<Car> list = carDao.getCar();
//        List<Cars> list = serviceDao.get(Cars.class);
        list = carDao.getCars();
//        list = repository.findAll();

        System.out.println("*********************************************");
//        list = carService.getAll();
        return new ModelAndView("car/viewcar", "list", list);
    }


    private void updateCarInList(Cars cars) {
        Cars carTemp = getCarById((long) cars.getId());
        carTemp.setName(cars.getName());
        carTemp.setModel(cars.getModel());
        carTemp.setRegistrationDate(cars.getRegistrationDate());
//        carTemp.setEmployees(cars.getEmployees());
    }

}

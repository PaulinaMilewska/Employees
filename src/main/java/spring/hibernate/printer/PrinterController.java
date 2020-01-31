package spring.hibernate.printer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.hibernate.CarEmployeeDao;
import spring.hibernate.DataSource;
import spring.hibernate.car.CarDao;
import spring.hibernate.car.Cars;
import spring.hibernate.employee.Employees;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PrinterController {

    private List<Printers> list;
//    private CarDao carDao;
    CarEmployeeDao carEmployeeDao;


    public PrinterController()  {

        try {
            carEmployeeDao = new CarEmployeeDao();
//            DataSource.supplyDatabase();
            list = carEmployeeDao.get(Printers.class);
        } catch (
                NullPointerException exception) {
            exception.getMessage();
            list = new ArrayList<>();
        }
    }


    @RequestMapping(value = "/printerform", method = RequestMethod.GET)
    public String showform(Model model) {
        model.addAttribute("printer", new Printers());
        return "printer/printerform";
    }

    @RequestMapping("/save_printer")
    public ModelAndView savePrinter(@ModelAttribute(value = "printer") Printers printers) {
        if (printers.getId() == 0) {
            if (DataSource.isDataBaseConnection) {
                carEmployeeDao.save(printers);
            }
            printers.setId(list.size());
            list.add(printers);

        } else {
            System.out.println();
            System.out.println("printer ID -------- "+printers.getId());
//            System.out.println("Employee ID -------- "+car.getEmployees().getId());
//            System.out.println("EMP__NAME: "+ car.getEmployees().getFirstName());
            if (DataSource.isDataBaseConnection) {
                printers.setEmployees(emp);
                carEmployeeDao.update(printers);

            }
            list.set(printers.getId()-1, printers);
            updatePrinterInList(printers);
        }
        return new ModelAndView("redirect:/viewprinter");
    }
    List<Employees> emp= new ArrayList<>();


    // doesn't edit last position
    @RequestMapping(value = "/edit_printer")
    public ModelAndView edit(@RequestParam(value = "car_id") String printer_id) {
        System.out.println();
        Printers printer = getPrinterById(Integer.parseInt(printer_id));
        System.out.println("printer_id: "+ printer_id);
        System.out.println("EMP_ID: "+ printer.getEmployees());
//        System.out.println("EMP__NAME: "+ printer.getEmployees().getFirstName());
        emp = printer.getEmployees();
//        if (DataSource.isDataBaseConnection) {
//            carEmployeeDao.update(car);
//        }
        return new ModelAndView("printer/printerform", "printer", printer);
    }


    private Printers getPrinterById(@RequestParam int printer_id) {
        return list.stream().filter(f -> f.getId() == printer_id).findFirst().get();
    }

    // doesn't delete last position
    @RequestMapping(value = "/delete_printer", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "printer_id") String printer_id) {
        System.out.println("printer1 id: "+ printer_id);
        Printers printerToDelete =  getPrinterById(Integer.parseInt(printer_id));
        System.out.println("printer2 id: "+ printer_id);
        if (DataSource.isDataBaseConnection) {
            carEmployeeDao.delete(printerToDelete);
        }
        list.remove(printerToDelete);
        return new ModelAndView("redirect:/viewprinter");
    }


    @RequestMapping("/viewprinter")
    public ModelAndView viewprinter(Model model) {
//        List<Car> list = carDao.getCar();
        List<Printers> list = carEmployeeDao.get(Printers.class);
        return new ModelAndView("printer/viewprinter", "list", list);
    }

    private void updatePrinterInList(Printers printer) {
        Printers printerTemp = getPrinterById(printer.getId());
        printer.setModel(printer.getModel());
        printer.setProducer(printer.getProducer());
//        carTemp.setEmployees(cars.getEmployees());
    }

}

package spring.hibernate.printer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.hibernate.DataSource;
import spring.hibernate.ServiceDao;
import spring.hibernate.employee.Employees;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PrinterController {
    private List<Printers> list;
    private PrinterDao printerDao;
    ServiceDao serviceDao;


    public PrinterController() {

        try {
            serviceDao = new ServiceDao();
//            DataSource.supplyDatabase();
            list = serviceDao.get(Printers.class);
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

    //doesn't work
    // todo : repair
    @RequestMapping("/save_printer")
    public ModelAndView savePrinter(@ModelAttribute(value = "printer") Printers printers) {
        if (printers.getId() == 0) {
            if (DataSource.isDataBaseConnection) {
                serviceDao.save(printers);
            }
            printers.setId(list.size());
            list.add(printers);

        } else {
            System.out.println();
            System.out.println("Printer ID -------- " + printers.getId());
//            System.out.println("Employee ID -------- "+printer.getEmployees().getId());
//            System.out.println("EMP__NAME: "+ printer.getEmployees().getFirstName());
            if (DataSource.isDataBaseConnection) {
                printers.setEmployees(emp);
                serviceDao.update(printers);

            }
            list.set(printers.getId() - 1, printers);
            updatePrinterInList(printers);
        }
        return new ModelAndView("redirect:/viewprinter");
    }

    Employees emp;


    @RequestMapping(value = "/edit_printer")
    public ModelAndView edit(@RequestParam(value = "printer_id") String printer_id) {
        System.out.println();
        Printers printers = getPrinterById(Integer.parseInt(printer_id));
        System.out.println("Printer_id: " + printer_id);
        System.out.println("EMP_ID: " + printers.getEmployees().getId());
        System.out.println("EMP__NAME: " + printers.getEmployees().getFirstName());
        emp = printers.getEmployees();
//        if (DataSource.isDataBaseConnection) {
//            serviceDao.update(printer);
//        }
        return new ModelAndView("printer/printerform", "printer", printers);
    }


    private Printers getPrinterById(@RequestParam int printer_id) {
        return list.stream().filter(f -> f.getId() == printer_id).findFirst().get();
    }


    @RequestMapping(value = "/delete_printer", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "printer_id") String printer_id) {
        System.out.println("Printer 1 id: " + printer_id);
        Printers printersToDelete = getPrinterById(Integer.parseInt(printer_id));
        System.out.println("Printer 2 id: " + printer_id);
        if (DataSource.isDataBaseConnection) {
            serviceDao.delete(printersToDelete);
        }
        list.remove(printersToDelete);
        return new ModelAndView("redirect:/viewprinter");
    }


    @RequestMapping("/viewprinter")
    public ModelAndView viewprinter(Model model) {
//        List<printer> list = printerDao.getprinter();
        List<Printers> list = serviceDao.get(Printers.class);
        return new ModelAndView("printer/viewprinter", "list", list);
    }

    private void updatePrinterInList(Printers printers) {
        Printers printersTemp = getPrinterById(printers.getId());
        printersTemp.setModel(printers.getModel());
        printersTemp.setProducer(printers.getProducer());
        //        printerTemp.setEmployees(printers.getEmployees());
    }

}

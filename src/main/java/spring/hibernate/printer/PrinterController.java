package spring.hibernate.printer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.hibernate.DataSource;
import spring.hibernate.ServiceDao;
import spring.hibernate.employee.Employees;
import spring.hibernate.printer.Printers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;

@Controller
public class PrinterController {
    private List<Printers> printersList;
    private List<Employees> employeesList;
    //    private PrinterDao printerDao;
    ServiceDao serviceDao;


    public PrinterController() {

        try {
            serviceDao = new ServiceDao();
//            DataSource.supplyDatabase();
            printersList = serviceDao.get(Printers.class);
            employeesList = serviceDao.get(Employees.class);
        } catch (
                NullPointerException exception) {
            exception.getMessage();
            employeesList = new ArrayList<>();
            printersList = new ArrayList<>();
        }
    }

    @RequestMapping(value = "/printerform", method = RequestMethod.GET)
    public String showform(Model model) {
        model.addAttribute("printer", new Printers());
        return "printer/printerform";
    }

    // todo : repair
    @RequestMapping("/save_printer")
    public ModelAndView save(@ModelAttribute(value = "printer") Printers printers,
                             @ModelAttribute(value = "employeesIds") ArrayList<Integer> employeesIds) {
        Set<Employees> employeesHashSet = new HashSet<>();
        for (Employees employees : employeesList) {
            if (printers.getId() == 0) {
                if (employeesIds.contains(employees.getId())) {
                    employeesHashSet.add(employees);
                }
            }
            printers.setEmployeesSet(employeesHashSet);

            if (printers.getId() == 0) {
                if (DataSource.isDataBaseConnection) {
                    serviceDao.save(printers);
                }
                printers.setId(printersList.size());
                printersList.add(printers);
            } else {
                System.out.println("Printer ID -------- " + printers.getId());

                if (DataSource.isDataBaseConnection) {
                    serviceDao.update(printers);
                }
                printersList.set(printers.getId() - 1, printers);
            }
        }
        return new ModelAndView("redirect:/viewprinters");
    }

    // todo : repair
    @PostMapping(value = "/edit_printer")
    public ModelAndView edit(@RequestParam(value = "printer_id") String printer_id) {
        Printers printer = getPrinterById(parseInt(printer_id));
        System.out.println(" Get Printer_id: " + printer_id);
        return new ModelAndView("printer/printerform", "printer", printer);
    }
    private Printers getPrinterById(@RequestParam int printer_id) {
        return printersList.stream().filter(f -> f.getId() == printer_id).findFirst().get();
    }

    @RequestMapping(value = "/delete_printer", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "printer_id") String printer_id) {
        System.out.println("Printer 1 id: " + printer_id);
        Printers printersToDelete = getPrinterById(parseInt(printer_id));
        if (DataSource.isDataBaseConnection) {
            serviceDao.delete(printersToDelete);
        }
        printersList.remove(printersToDelete);
        return new ModelAndView("redirect:/viewprinters");
    }

    @RequestMapping("/viewprinters")
    public ModelAndView viewprinter(Model model) {
        return new ModelAndView("viewprinters", "printersList", printersList);
    }
}

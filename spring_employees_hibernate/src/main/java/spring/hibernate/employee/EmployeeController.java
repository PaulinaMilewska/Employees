package spring.hibernate.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @RequestMapping(value = "/empform")
    public String showform(Model model) {
        model.addAttribute("emp", new Employee());
        return "emp/empform";
    }

    @RequestMapping(value = "/save_emp", method = RequestMethod.POST)
//    public ModelAndView save(@ModelAttribute(value = "employees") Employees employees) {
    public String save(@ModelAttribute(value = "employees") Employee employee) {
//        EmployeeController employeeController = new EmployeeController();
        System.out.println("Add new employee");
        Long size = Long.valueOf(service.listAll().size());
      employee.setId(size+1);
        service.save(employee);
        return "redirect:/viewemp";

//        if (employees.getId() == 0) {
//            System.out.println("Adding a new emp");
//            employees.setId(employees.getId() + 1);
//            employeeController.saveEmployee(employees);
//        } else {
//            employeeController.updateEmployees(employees);
//        }
//        return new ModelAndView("redirect:/viewemp");
    }

//    public void saveEmployee(Employees employee) {
//        Transaction transaction = null;
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.save(employee);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }

//    @RequestMapping("/viewemp")
//    public ModelAndView viewemp() {
////        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
////            String hql = "FROM Employees";
//            List<Employees> list = service.listAll();
////            List<Employees> list = session.createQuery(hql).list();
////            return session.createQuery(hql).list();
//
//            return new ModelAndView("emp/viewemp", "list", list);
////        }
//    }

    @RequestMapping("/viewemp")
    public String viewemp(Model model) {
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            String hql = "FROM Employees";
        List<Employee> list = service.listAll();
//            List<Employees> list = session.createQuery(hql).list();
//            return session.createQuery(hql).list();
        model.addAttribute("list", list);

        return "emp/viewemp";
//        }
    }


//    public List<Employees> getEmployees() {
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            String hql = "FROM Employees";
//            return session.createQuery(hql).list();
//        }
//    }

//    public void updateEmployees(Employees employee) {
//        Transaction transaction = null;
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.update(employee);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }

//    public void deleteEmployees(int id) {
//        Session session = HibernateConfig.getSessionFactory().openSession();
//        Transaction transaction = null;
//        try  {
//            transaction = session.beginTransaction();
//            Employees employee = session.find(Employees.class, id);
//            session.delete(employee);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }


}

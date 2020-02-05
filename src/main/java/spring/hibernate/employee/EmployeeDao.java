package spring.hibernate.employee;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spring.hibernate.HibernateConfig;
import spring.hibernate.printer.Printers;

import java.util.List;

//@Component
@Service
public class EmployeeDao {
    public EmployeeDao() {
    }

    List<Employees> list;
    @Autowired
    EmployeesRepository repository;

    public void saveEmployee(Employees employee) {
        repository.save(employee);
    }

//    public void saveEmployee(Employees employee) {
//        Transaction transaction = null;
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
////            employee.setId(employee.getId()+1);
//            session.save(employee);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }

    public List<Employees> getEmployees() {
        return repository.findAll();
    }
//
//    public List<Employees> getEmployees() {
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            return session.createQuery("FROM  Employees", Employees.class).list();
//        }
//    }

    public void updateEmployees(Employees employee) {
        Employees employeeToUpdate = repository.findOne(employee.getId());
        Employees employeeNew = new Employees();
        employeeNew.setId(employeeToUpdate.getId());
        employeeNew.setFirstName(employeeToUpdate.getFirstName());
        employeeNew.setAddress(employeeToUpdate.getAddress());
        employeeNew.setAge(employeeToUpdate.getAge());
        employeeNew.setBenefit(employeeToUpdate.getBenefit());
        employeeNew.setCars(employeeToUpdate.getCars());
        employeeNew.setCity(employeeToUpdate.getCity());
        employeeNew.setEmail(employeeToUpdate.getEmail());
        employeeNew.setPrinters(employeeToUpdate.getPrinters());
        employeeNew.setSalary(employeeToUpdate.getSalary());
        employeeNew.setStartJobDate(employeeToUpdate.getStartJobDate());

    }


    //
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
    public Employees getEmployeesById(Integer employee_id) {
        return repository.findOne(Long.valueOf(employee_id));
    }

//
//    public Employees getEmployeesById(Integer employee_id) {
//        {
//            try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//                return session.get(Employees.class, employee_id);
//            }
//        }
//    }
//

    public void deleteEmployee(Employees employeeToDelete) {
        repository.delete(employeeToDelete.getId());
    }

//    public void deleteEmployee(Employees employeeToDelete) {
//        Transaction transaction = null;
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.delete(employeeToDelete);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }
}

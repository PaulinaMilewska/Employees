package spring.hibernate.employee;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import spring.hibernate.HibernateConfig;

import java.util.List;

@Component
public class EmployeeDao {


    public void saveEmployee(Employees employee) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Employees> getEmployees() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery(" FROM  Employees", Employees.class).list();
        }
    }

    public void updateEmployees(Employees employee) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Employees getEmployeesById(Integer employee_id) {
        {
            try (Session session = HibernateConfig.getSessionFactory().openSession()) {
                return session.get(Employees.class, employee_id);
            }
        }
    }

    public void deleteEmployee(Employees employeeToDelete) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(employeeToDelete);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

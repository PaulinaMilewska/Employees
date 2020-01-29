package spring.hibernate.printer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import spring.hibernate.HibernateConfig;
import spring.hibernate.TypeObject;

import java.util.List;

@Component
public class PrinterDao implements TypeObject {

    public PrinterDao() {
    }

    public void savePrinter(Printers printers) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
//           printer.setId(printer.getId()+2);
            session.save(printers);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public List<Printers> getPrinter() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("FROM Printer", Printers.class).list();
        }
    }

    public void updatePrinter(Printers printers) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(printers);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Printers getPrinterById(Integer printer_id) {
        {
            try (Session session = HibernateConfig.getSessionFactory().openSession()) {
                return session.get(Printers.class, printer_id);
            }
        }
    }

    public void deletePrinter(Printers printersToDelete) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(printersToDelete);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

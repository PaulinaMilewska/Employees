package spring.hibernate.printer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spring.hibernate.HibernateConfig;
import spring.hibernate.employee.Employees;


import java.util.List;

//@Component
@Service
public class PrinterDao {

    public PrinterDao() {
    }

    @Autowired
    PrintersRepository repository;
    List<Employees> list;

    public void savePrinter(Printers printers){
        repository.save(printers);
    }

//    public void savePrinter(Printers printers) {
//        Transaction transaction = null;
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
////            employee.setId(employee.getId()+1);
//            session.save(printers);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }

    public List<Printers> getPrinters(){
      return   repository.findAll();
    }

//    public List<Printers> getPrinters() {
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            return session.createQuery("FROM  Printer", Printers.class).list();
//        }
//    }


    public void updatePrinters(Printers printers){
       Printers printersToUpdate = repository.findOne(printers.getId());
       Printers printersNew = new Printers();
       printersNew.setId(printersToUpdate.getId());
       printersNew.setProducer(printersToUpdate.getProducer());
       printersNew.setModel(printersToUpdate.getModel());
       printersNew.setEmployees(printersToUpdate.getEmployees());

    }

//    public void updatePrinters(Printers printers) {
//        Transaction transaction = null;
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.update(printers);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }


    public Printers getPrintersById(Integer printers_id){
        return repository.findOne(Long.valueOf(printers_id));
    }

//    public Printers getPrintersById(Integer printers_id) {
//        {
//            try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//                return session.get(Printers.class, printers_id);
//            }
//        }
//    }

    public void deletePrinter(Printers printersToDelete){
        repository.delete(printersToDelete.getId());
    }

//    public void deletePrinter(Printers printersToDelete) {
//        Transaction transaction = null;
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.delete(printersToDelete);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }


}

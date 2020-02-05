package spring.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

//@Service
//@Configurable
public class CarEmployeeDao {
    // OK
//    @Autowired
//    private TypeObjectRepository repository;
    // OK
//    public void save(TypeObject object){
//        repository.save(object);
//    }

//    public  void save(TypeObject object) {
//        Transaction transaction = null;
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.save(object);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }
// OK
//    public  void saveList(List<TypeObject> object) {
//        repository.findAll();
//    }

//    public  void saveList(List<TypeObject> object) {
//        Transaction transaction = null;
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.save(object);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }

    // OK
//    public  <T> List<T> get(Class<T> type) {
//        return (List<T>) repository.findAll();
//    }
    // OK
//    public TypeObject get (Long id){
//        return repository.getOne(id);
//    }

//    public  <T> List<T> get(Class<T> type) {
//        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<T> criteria = builder.createQuery(type);
//        criteria.from(type);
//        List<T> data = session.createQuery(criteria).getResultList();
//        tx.commit();
//        return data;
//    }




//    public  void update(TypeObject object){
//        repository.saveAndFlush(object);
//    }




//    public  void update(TypeObject object) {
//        Transaction transaction = null;
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.update(object);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }
// OK
//    public  void delete(TypeObject object){
//        repository.delete(object);
//    }

//    public  void delete(TypeObject object) {
//        Transaction transaction = null;
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.delete(object);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }
}




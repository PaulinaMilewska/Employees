package spring.hibernate.car;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import spring.hibernate.HibernateConfig;
import spring.hibernate.TypeObject;

import java.util.List;

@Component
public class CarDao implements TypeObject {
    public CarDao() {
    }

    public void saveCar(Cars car) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
//           car.setId(car.getId()+2);
            session.save(car);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Cars> getCar() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("FROM Car", Cars.class).list();
        }
    }


    public void updateCar(Cars car) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(car);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Cars getCarById(Integer car_id) {
        {
            try (Session session = HibernateConfig.getSessionFactory().openSession()) {
                return session.get(Cars.class, car_id);
            }
        }
    }

    public void deleteCar(Cars carToDelete) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(carToDelete);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

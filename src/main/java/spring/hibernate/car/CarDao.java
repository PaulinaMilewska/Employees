package spring.hibernate.car;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spring.hibernate.CarEmployeeDao;
import spring.hibernate.HibernateConfig;
import spring.hibernate.TypeObject;
import spring.hibernate.employee.Employees;

import java.util.List;

//@Component
@Service
public class CarDao {
    @Autowired
    CarsRepository repository;

    public CarDao() {
    }

    public void saveCar(Cars car) {
        repository.save(car);
    }


    //    public void saveCar(Cars car) {
//        Transaction transaction = null;
//        try (Session session = HibernateConfig.getSessionFactory().openSession()){
//            transaction = session.beginTransaction();
////           car.setId(car.getId()+2);
//            session.save(car);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }
//
//
    public List<Cars> getCars() {
        return repository.findAll();
    }
//
//    public List<Cars> getCar() {
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            return session.createQuery("FROM Car", Cars.class).list();
//        }
//    }
//

    public void updateCar(Cars car){
        Cars carToUpdate = repository.findOne(car.getId());
        Cars carNew = new Cars();

        carNew.setId(carToUpdate.getId());
        carNew.setName(carNew.getName());
        carNew.setEmployees(carNew.getEmployees());
        carNew.setRegistrationDate(carNew.getRegistrationDate());
        carNew.setModel(carNew.getModel());
    }

//    public void updateCar(Cars car) {
//        Transaction transaction = null;
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.update(car);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }
//

    public Cars getCarById(Integer car_id){
        return repository.getOne(Long.valueOf(car_id));
    }
//    public Cars getCarById(Integer car_id) {
//        {
//            try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//                return session.get(Cars.class, car_id);
//            }
//        }
//    }
//

    public void deleteCar(Cars carToDelete){
        repository.delete(carToDelete);
    }

//    public void deleteCar(Cars carToDelete) {
//        Transaction transaction = null;
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.delete(carToDelete);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }
}

package spring.hibernate.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import spring.hibernate.employee.Employee;
import spring.hibernate.employee.EmployeeRepository;

import java.util.List;

@Service
public class CarService {

//    @Bean(name="entityManagerFactory")
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//
//        return sessionFactory;
//    }

    @Autowired
    private CarRepository repo;

    public List<Car> listAll() {
        return (List<Car>) repo.findAll();
    }

    public void save(Car car) {
        repo.save(car);
    }

    public Car get(Long id){
        return repo.findOne(id);
    }

    public void delete(Long id){
        repo.delete(id);
    }

}

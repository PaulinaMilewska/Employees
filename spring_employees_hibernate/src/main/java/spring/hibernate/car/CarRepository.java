package spring.hibernate.car;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import spring.hibernate.employee.Employee;


//@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    Iterable<Car> findAllByEmployee(Employee employee);

    Long countByEmployee(Employee employee);

}

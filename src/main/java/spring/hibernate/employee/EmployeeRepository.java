package spring.hibernate.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.hibernate.car.Cars;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees,Long>
{
    @Override
    List<Employees> findAll();
}

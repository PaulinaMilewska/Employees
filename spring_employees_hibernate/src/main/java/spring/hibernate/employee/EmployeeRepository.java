package spring.hibernate.employee;

import jdk.nashorn.internal.runtime.options.Option;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

//@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long> {

    List<Employee> findAllBy(Long id);
//    @EntityGraph(value = "Employee.cars", type = EntityGraph.EntityGraphType.LOAD)
//    Option<Employee> readById(Long id);

}

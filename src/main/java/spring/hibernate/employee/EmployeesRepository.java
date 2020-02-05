package spring.hibernate.employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository
        extends JpaRepository<Employees,Long>
{
}

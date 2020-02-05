package spring.hibernate.car;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepository
        extends JpaRepository<Cars,Long>
{
}

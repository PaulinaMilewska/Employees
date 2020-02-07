package spring.hibernate.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Cars,Long> {
//public interface CarRepository extends CrudRepository<Cars, Long> {
        @Override
    List<Cars> findAll();
}
//public interface CarRepository extends CrudRepository<Cars, Long> {
//    @Override
//    List<Cars> findAll();
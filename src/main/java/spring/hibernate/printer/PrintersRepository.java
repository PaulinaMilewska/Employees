package spring.hibernate.printer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PrintersRepository extends JpaRepository<Printers ,Long>  {
// CrudRepository<Printers, Long>
}

package spring.hibernate.car;

import java.util.List;

public interface CarService {
    void create(Cars cars);

    List<Cars> getAll();

    void update(Cars cars);

    void delete(Cars cars);


}

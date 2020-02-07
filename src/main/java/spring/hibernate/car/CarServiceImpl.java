package spring.hibernate.car;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void create(Cars cars) {
        carRepository.save(cars);
    }

    @Override
    public List<Cars> getAll() {
        return carRepository.findAll();
    }

    @Override
    public void update(Cars cars) {
        carRepository.save(cars);
    }

    @Override
    public void delete(Cars cars) {
        carRepository.delete(cars);
    }
}

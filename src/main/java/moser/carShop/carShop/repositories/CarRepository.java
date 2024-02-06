package moser.carShop.carShop.repositories;

import moser.carShop.carShop.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}

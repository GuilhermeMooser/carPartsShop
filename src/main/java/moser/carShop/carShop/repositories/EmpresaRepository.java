package moser.carShop.carShop.repositories;

import moser.carShop.carShop.entities.Car;
import moser.carShop.carShop.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}

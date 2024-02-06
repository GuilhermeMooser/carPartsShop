package moser.carShop.carShop.repositories;

import moser.carShop.carShop.entities.Car;
import moser.carShop.carShop.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}

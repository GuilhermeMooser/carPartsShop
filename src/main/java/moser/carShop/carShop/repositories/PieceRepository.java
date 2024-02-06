package moser.carShop.carShop.repositories;

import moser.carShop.carShop.entities.Car;
import moser.carShop.carShop.entities.Piece;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceRepository extends JpaRepository<Piece, Long> {
}

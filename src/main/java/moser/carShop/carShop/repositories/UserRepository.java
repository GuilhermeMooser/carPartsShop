package moser.carShop.carShop.repositories;

import moser.carShop.carShop.entities.Piece;
import moser.carShop.carShop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

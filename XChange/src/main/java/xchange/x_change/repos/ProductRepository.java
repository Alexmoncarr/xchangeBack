package xchange.x_change.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import xchange.x_change.domain.Product;
import xchange.x_change.domain.User;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findFirstByUser(User user);

    List<Product> findByProductNameContainingIgnoreCase(String productName);

}

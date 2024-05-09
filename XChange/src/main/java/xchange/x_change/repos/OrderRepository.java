package xchange.x_change.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import xchange.x_change.domain.Order;
import xchange.x_change.domain.Product;


public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findFirstByOrderProductProducts(Product product);

    List<Order> findAllByOrderProductProducts(Product product);
}

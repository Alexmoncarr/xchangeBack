package xchange.x_change.service;

import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xchange.x_change.domain.Order;
import xchange.x_change.domain.Product;
import xchange.x_change.model.OrderDTO;
import xchange.x_change.repos.OrderRepository;
import xchange.x_change.repos.ProductRepository;
import xchange.x_change.util.NotFoundException;


@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(final OrderRepository orderRepository,
            final ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<OrderDTO> findAll() {
        final List<Order> orders = orderRepository.findAll(Sort.by("id"));
        return orders.stream()
                .map(order -> mapToDTO(order, new OrderDTO()))
                .toList();
    }

    public OrderDTO get(final Integer id) {
        return orderRepository.findById(id)
                .map(order -> mapToDTO(order, new OrderDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final OrderDTO orderDTO) {
        final Order order = new Order();
        mapToEntity(orderDTO, order);
        return orderRepository.save(order).getId();
    }

    public void update(final Integer id, final OrderDTO orderDTO) {
        final Order order = orderRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(orderDTO, order);
        orderRepository.save(order);
    }

    public void delete(final Integer id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO mapToDTO(final Order order, final OrderDTO orderDTO) {
        orderDTO.setId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setOrderProductProducts(order.getOrderProductProducts().stream()
                .map(product -> product.getId())
                .toList());
        return orderDTO;
    }

    private Order mapToEntity(final OrderDTO orderDTO, final Order order) {
        order.setOrderDate(orderDTO.getOrderDate());
        final List<Product> orderProductProducts = productRepository.findAllById(
                orderDTO.getOrderProductProducts() == null ? Collections.emptyList() : orderDTO.getOrderProductProducts());
        if (orderProductProducts.size() != (orderDTO.getOrderProductProducts() == null ? 0 : orderDTO.getOrderProductProducts().size())) {
            throw new NotFoundException("one of orderProductProducts not found");
        }
        order.setOrderProductProducts(new HashSet<>(orderProductProducts));
        return order;
    }

}

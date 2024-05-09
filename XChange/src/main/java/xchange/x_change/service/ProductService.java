package xchange.x_change.service;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xchange.x_change.domain.Product;
import xchange.x_change.domain.User;
import xchange.x_change.model.ProductDTO;
import xchange.x_change.repos.OrderRepository;
import xchange.x_change.repos.ProductRepository;
import xchange.x_change.repos.UserRepository;
import xchange.x_change.util.NotFoundException;


@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public ProductService(final ProductRepository productRepository,
            final UserRepository userRepository, final OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public List<ProductDTO> findAll() {
        final List<Product> products = productRepository.findAll(Sort.by("id"));
        return products.stream()
                .map(product -> mapToDTO(product, new ProductDTO()))
                .toList();
    }

    public ProductDTO get(final Integer id) {
        return productRepository.findById(id)
                .map(product -> mapToDTO(product, new ProductDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ProductDTO productDTO) {
        final Product product = new Product();
        mapToEntity(productDTO, product);
        return productRepository.save(product).getId();
    }

    public void update(final Integer id, final ProductDTO productDTO) {
        final Product product = productRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(productDTO, product);
        productRepository.save(product);
    }

    public void delete(final Integer id) {
        final Product product = productRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        // remove many-to-many relations at owning side
        orderRepository.findAllByOrderProductProducts(product)
                .forEach(order -> order.getOrderProductProducts().remove(product));
        productRepository.delete(product);
    }

    private ProductDTO mapToDTO(final Product product, final ProductDTO productDTO) {
        productDTO.setId(product.getId());
        productDTO.setProductName(product.getProductName());
        productDTO.setPrice(product.getPrice());
        productDTO.setUser(product.getUser() == null ? null : product.getUser().getId());
        return productDTO;
    }

    private Product mapToEntity(final ProductDTO productDTO, final Product product) {
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        final User user = productDTO.getUser() == null ? null : userRepository.findById(productDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        product.setUser(user);
        return product;
    }

    public List<ProductDTO> searchByName(String productName) {
        List<Product> products = productRepository.findByProductNameContainingIgnoreCase(productName);
        return products.stream()
            .map(product -> mapToDTO(product, new ProductDTO()))
            .toList();
    }


}
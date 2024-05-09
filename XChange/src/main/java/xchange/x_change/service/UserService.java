package xchange.x_change.service;

import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import xchange.x_change.domain.Event;
import xchange.x_change.domain.Product;
import xchange.x_change.domain.Review;
import xchange.x_change.domain.Role;
import xchange.x_change.domain.User;
import xchange.x_change.model.UserDTO;
import xchange.x_change.repos.EventRepository;
import xchange.x_change.repos.ProductRepository;
import xchange.x_change.repos.ReviewRepository;
import xchange.x_change.repos.RoleRepository;
import xchange.x_change.repos.UserRepository;
import xchange.x_change.util.NotFoundException;
import xchange.x_change.util.ReferencedWarning;


@Service
@Transactional
public class UserService {

    public UserDetailsService userDetailsService();

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EventRepository eventRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public UserService(final UserRepository userRepository, final RoleRepository roleRepository,
            final EventRepository eventRepository, final ProductRepository productRepository,
            final ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.eventRepository = eventRepository;
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("id"));
        return users.stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .toList();
    }


    public UserDTO get(final Integer id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }

    public void update(final Integer id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Integer id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirthdate(user.getBirthdate());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserRoleRoles(user.getUserRoleRoles().stream()
                .map(role -> role.getId())
                .toList());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setBirthdate(userDTO.getBirthdate());
        user.setPassword(userDTO.getPassword());
        final List<Role> userRoleRoles = roleRepository.findAllById(
                userDTO.getUserRoleRoles() == null ? Collections.emptyList() : userDTO.getUserRoleRoles());
        if (userRoleRoles.size() != (userDTO.getUserRoleRoles() == null ? 0 : userDTO.getUserRoleRoles().size())) {
            throw new NotFoundException("one of userRoleRoles not found");
        }
        user.setUserRoleRoles(new HashSet<>(userRoleRoles));
        return user;
    }

    public boolean usernameExists(final String username) {
        return userRepository.existsByUsernameIgnoreCase(username);
    }

    public boolean emailExists(final String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    public ReferencedWarning getReferencedWarning(final Integer id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Event adminEvent = eventRepository.findFirstByAdmin(user);
        if (adminEvent != null) {
            referencedWarning.setKey("user.event.admin.referenced");
            referencedWarning.addParam(adminEvent.getId());
            return referencedWarning;
        }
        final Product userProduct = productRepository.findFirstByUser(user);
        if (userProduct != null) {
            referencedWarning.setKey("user.product.user.referenced");
            referencedWarning.addParam(userProduct.getId());
            return referencedWarning;
        }
        final Review reviewerReview = reviewRepository.findFirstByReviewer(user);
        if (reviewerReview != null) {
            referencedWarning.setKey("user.review.reviewer.referenced");
            referencedWarning.addParam(reviewerReview.getId());
            return referencedWarning;
        }
        final Review revieweeReview = reviewRepository.findFirstByReviewee(user);
        if (revieweeReview != null) {
            referencedWarning.setKey("user.review.reviewee.referenced");
            referencedWarning.addParam(revieweeReview.getId());
            return referencedWarning;
        }
        return null;
    }

}
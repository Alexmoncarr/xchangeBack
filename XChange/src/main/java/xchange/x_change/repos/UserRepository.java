package xchange.x_change.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import xchange.x_change.domain.Role;
import xchange.x_change.domain.User;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findFirstByUserRoleRoles(Role role);

    List<User> findAllByUserRoleRoles(Role role);

    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByEmailIgnoreCase(String email);

    Optional <User> findByUsername(String username);
    // En UserRepository

    Object findFirsByEmail(String username);

}

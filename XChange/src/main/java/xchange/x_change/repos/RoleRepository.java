package xchange.x_change.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import xchange.x_change.domain.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {
}

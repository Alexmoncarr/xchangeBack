package xchange.x_change.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xchange.x_change.models.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}

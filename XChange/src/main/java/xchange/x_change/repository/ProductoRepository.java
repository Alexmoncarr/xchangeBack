package xchange.x_change.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xchange.x_change.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNombreContainingOrDescripcionContaining(String nombre, String descripcion);
    List<Producto> findByCategoria(String categoria);
}

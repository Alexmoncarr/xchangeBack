package xchange.x_change.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xchange.x_change.models.Valoracion;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {
}
package xchange.x_change.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xchange.x_change.repository.ValoracionRepository;
import xchange.x_change.models.Producto;
import xchange.x_change.models.Valoracion;
import xchange.x_change.service.ValoracionService;

@Service
public class ValoracionServiceImpl implements ValoracionService {

    private final ValoracionRepository valoracionRepository;

    public ValoracionServiceImpl(ValoracionRepository valoracionRepository) {
        this.valoracionRepository = valoracionRepository;
    }

    @Override
    @Transactional
    public Valoracion createValoracion(Valoracion valoracion) {
        return valoracionRepository.save(valoracion);
    }

    @Override
    @Transactional
    public void deleteValoracionById(Long id) {
        Valoracion valoracion = valoracionRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Valoración con id " + id + " no encontrado"));
        valoracionRepository.delete(valoracion);
    }
}
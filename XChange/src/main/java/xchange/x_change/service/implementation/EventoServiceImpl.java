package xchange.x_change.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xchange.x_change.models.Evento;
import xchange.x_change.models.Usuario;
import xchange.x_change.repository.EventoRepository;
import xchange.x_change.service.EventoService;

@Service
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;

    public EventoServiceImpl(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Override
    @Transactional
    public Evento createEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Override
    @Transactional
    public void registerUsuarioToEvento(Long eventoId, Usuario usuario) {
        Evento evento = eventoRepository.findById(eventoId)
            .orElseThrow(() -> new IllegalStateException("Evento no encontrado"));
        evento.getParticipantes().add(usuario);
        eventoRepository.save(evento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Evento> findAllEventos() {
        return eventoRepository.findAll();
    }

    @Override
    @Transactional
    public Evento updateEvento(Long id, Evento eventoDetails) {
        Evento evento = eventoRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Evento no encontrado"));

        evento.setNombre(eventoDetails.getNombre());
        evento.setFecha(eventoDetails.getFecha());
        evento.setDescripcion(eventoDetails.getDescripcion());
        // Actualiza otros campos necesarios

        return eventoRepository.save(evento);
    }


}
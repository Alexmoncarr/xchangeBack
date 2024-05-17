package xchange.x_change.service;

import java.util.List;

import xchange.x_change.models.Evento;
import xchange.x_change.models.Usuario;

public interface EventoService {
    Evento createEvento(Evento evento);
    void registerUsuarioToEvento(Long eventoId, Usuario usuario);
    List<Evento> findAllEventos();
}

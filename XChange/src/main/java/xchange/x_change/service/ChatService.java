package xchange.x_change.service;

import java.util.List;

import xchange.x_change.models.Chat;
import xchange.x_change.models.Mensaje;
import xchange.x_change.models.Usuario;

public interface ChatService {
    Chat createChat(Usuario usuario1, Usuario usuario2);
    Mensaje sendMensaje(Long chatId, Usuario sender, String contenido);
    List<Mensaje> getMensajesByChat(Long chatId);
}

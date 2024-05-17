package xchange.x_change.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xchange.x_change.models.Chat;
import xchange.x_change.models.Mensaje;
import xchange.x_change.models.Usuario;
import xchange.x_change.repository.ChatRepository;
import xchange.x_change.repository.MensajeRepository;
import xchange.x_change.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final MensajeRepository mensajeRepository;

    public ChatServiceImpl(ChatRepository chatRepository, MensajeRepository mensajeRepository) {
        this.chatRepository = chatRepository;
        this.mensajeRepository = mensajeRepository;
    }

    @Override
    @Transactional
    public Chat createChat(Usuario usuario1, Usuario usuario2) {
        Chat chat = new Chat();
        chat.setUsuario1(usuario1);
        chat.setUsuario2(usuario2);
        return chatRepository.save(chat);
    }

    @Override
    @Transactional
    public Mensaje sendMensaje(Long chatId, Usuario sender, String contenido) {
        Chat chat = chatRepository.findById(chatId)
            .orElseThrow(() -> new IllegalArgumentException("Chat no encontrado"));
        Mensaje mensaje = new Mensaje();
        mensaje.setContenido(contenido);
        mensaje.setSender(sender);
        mensaje.setChat(chat);
        return mensajeRepository.save(mensaje);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mensaje> getMensajesByChat(Long chatId) {
        return mensajeRepository.findAllByChatId(chatId);
    }
}
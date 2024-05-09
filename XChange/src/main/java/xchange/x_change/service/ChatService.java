package xchange.x_change.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import xchange.x_change.domain.Chat;
import xchange.x_change.model.ChatDTO;
import xchange.x_change.repos.ChatRepository;
import xchange.x_change.repos.UserRepository;
import xchange.x_change.util.NotFoundException;

@Service
public class ChatService {
     private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public ChatService(final ChatRepository chatRepository,
                       final UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public List<ChatDTO> findAll() {
        final List<Chat> chats = chatRepository.findAll(Sort.by("id"));
        return chats.stream()
                .map(chat -> mapToDTO(chat, new ChatDTO()))
                .toList();
    }

    public ChatDTO get(final Integer id) {
        return chatRepository.findById(id)
                .map(chat -> mapToDTO(chat, new ChatDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ChatDTO chatDTO) {
        final Chat chat = new Chat();
        mapToEntity(chatDTO, chat);
        return chatRepository.save(chat).getId();
    }

    public void update(final Integer id, final ChatDTO chatDTO) {
        final Chat chat = chatRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(chatDTO, chat);
        chatRepository.save(chat);
    }

    public void delete(final Integer id) {
        chatRepository.deleteById(id);
    }

    private ChatDTO mapToDTO(final Chat chat, final ChatDTO chatDTO) {
        chatDTO.setId(chat.getId());
        chatDTO.setSender(chat.getSender().getId());
        chatDTO.setReceiver(chat.getReceiver().getId());
        chatDTO.setMessage(chat.getMessage());
        chatDTO.setTimestamp(chat.getTimestamp());
        return chatDTO;
    }

    private Chat mapToEntity(final ChatDTO chatDTO, final Chat chat) {
        chat.setSender(userRepository.findById(chatDTO.getSender())
                .orElseThrow(() -> new NotFoundException("sender not found")));
        chat.setReceiver(userRepository.findById(chatDTO.getReceiver())
                .orElseThrow(() -> new NotFoundException("receiver not found")));
        chat.setMessage(chatDTO.getMessage());
        chat.setTimestamp(chatDTO.getTimestamp());
        return chat;
    }
}

package xchange.x_change.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xchange.x_change.domain.Event;
import xchange.x_change.domain.User;
import xchange.x_change.model.EventDTO;
import xchange.x_change.repos.EventRepository;
import xchange.x_change.repos.UserRepository;
import xchange.x_change.util.NotFoundException;


@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventService(final EventRepository eventRepository,
            final UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<EventDTO> findAll() {
        final List<Event> events = eventRepository.findAll(Sort.by("id"));
        return events.stream()
                .map(event -> mapToDTO(event, new EventDTO()))
                .toList();
    }

    public EventDTO get(final Integer id) {
        return eventRepository.findById(id)
                .map(event -> mapToDTO(event, new EventDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final EventDTO eventDTO) {
        final Event event = new Event();
        mapToEntity(eventDTO, event);
        return eventRepository.save(event).getId();
    }

    public void update(final Integer id, final EventDTO eventDTO) {
        final Event event = eventRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(eventDTO, event);
        eventRepository.save(event);
    }

    public void delete(final Integer id) {
        eventRepository.deleteById(id);
    }

    private EventDTO mapToDTO(final Event event, final EventDTO eventDTO) {
        eventDTO.setId(event.getId());
        eventDTO.setEventName(event.getEventName());
        eventDTO.setEventDate(event.getEventDate());
        eventDTO.setAdmin(event.getAdmin() == null ? null : event.getAdmin().getId());
        return eventDTO;
    }

    private Event mapToEntity(final EventDTO eventDTO, final Event event) {
        event.setEventName(eventDTO.getEventName());
        event.setEventDate(eventDTO.getEventDate());
        final User admin = eventDTO.getAdmin() == null ? null : userRepository.findById(eventDTO.getAdmin())
                .orElseThrow(() -> new NotFoundException("admin not found"));
        event.setAdmin(admin);
        return event;
    }

}
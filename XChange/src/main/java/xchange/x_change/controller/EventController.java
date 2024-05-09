package xchange.x_change.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xchange.x_change.domain.User;
import xchange.x_change.model.EventDTO;
import xchange.x_change.repos.UserRepository;
import xchange.x_change.service.EventService;
import xchange.x_change.util.CustomCollectors;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/events", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController{

    private final EventService eventService;
    private final UserRepository userRepository;

    public EventController(final EventService eventService, final UserRepository userRepository) {
        this.eventService = eventService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(eventService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createEvent(@RequestBody @Valid final EventDTO eventDTO) {
        final Integer createdId = eventService.create(eventDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateEvent(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final EventDTO eventDTO) {
        eventService.update(id, eventDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable(name = "id") final Integer id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/adminValues")
    public ResponseEntity<Map<Integer, String>> getAdminValues() {
        return ResponseEntity.ok(userRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(User::getId, User::getUsername)));
    }

}

package com.xchange.xchange.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xchange.xchange.repositories.EventRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.xchange.xchange.exceptions.EventNotFoundException;
import com.xchange.xchange.models.Event;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/events")
public class EventController {
    
    @Autowired
    private EventRepository eventRepository;
    

    @GetMapping()
    public List<Event> getAllEvents () {
        return eventRepository.findAll();
    }
    
    @GetMapping("/{idE}")
    public ResponseEntity<Event> getEventById(@PathVariable Long idE) {
        Event event = eventRepository.findById(idE)
                        .orElseThrow(() -> new EventNotFoundException("Event with id " + idE + " not found"));
        return ResponseEntity.ok().body(event);
    }

    @GetMapping("/search")
    public List<Event> searchEvents(@RequestParam String title) {
        return eventRepository.findByTitle(title);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventRepository.save(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

    @PutMapping("/{idE}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long idE, @RequestBody Event eventDetails) {
        Event event = eventRepository.findById(idE)
            .orElseThrow(() -> new EventNotFoundException("Event with id " + idE + " was not found"));
        event.setTitle(eventDetails.getTitle());
        event.setDescription(eventDetails.getDescription());
        event.setDate(eventDetails.getDate());
        final Event updatedEvent = eventRepository.save(event);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{idE}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long idE) {
        Event event = eventRepository.findById(idE)
            .orElseThrow(() -> new EventNotFoundException("Event with id " + idE + " not found"));
            eventRepository.delete(event);
            return ResponseEntity.ok().build();
    }
    

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<String> handleEventNotFoundException(EventNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    
    
}

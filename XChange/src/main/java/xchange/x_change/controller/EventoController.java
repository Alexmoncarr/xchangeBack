package xchange.x_change.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xchange.x_change.models.Evento;
import xchange.x_change.service.EventoService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value="api/eventos")
@CrossOrigin(origins = "http://localhost:4200")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        Evento newEvento = eventoService.createEvento(evento);
        return ResponseEntity.ok(newEvento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Evento>> getAllEventos() {
        return ResponseEntity.ok(eventoService.findAllEventos());
    }

    @PutMapping("path/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Long id, @RequestBody Evento eventoDetails) {
        Evento updatedEvento = eventoService.updateEvento(id, eventoDetails);
       return ResponseEntity.ok(updatedEvento);
    }
}
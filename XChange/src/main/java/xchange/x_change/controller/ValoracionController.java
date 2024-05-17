package xchange.x_change.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xchange.x_change.models.Valoracion;
import xchange.x_change.service.ValoracionService;

@RestController
@RequestMapping("/api/valoraciones")
@CrossOrigin(origins = "http://localhost:4200")
public class ValoracionController {

    private final ValoracionService valoracionService;

    public ValoracionController(ValoracionService valoracionService) {
        this.valoracionService = valoracionService;
    }

    @PostMapping
    public ResponseEntity<Valoracion> createValoracion(@RequestBody Valoracion valoracion) {
        Valoracion nuevaValoracion = valoracionService.createValoracion(valoracion);
        return ResponseEntity.ok(nuevaValoracion);
    }
}
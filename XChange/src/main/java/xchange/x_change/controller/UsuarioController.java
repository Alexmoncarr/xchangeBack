package xchange.x_change.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xchange.x_change.models.Usuario;
import xchange.x_change.service.IUsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

  private final IUsuarioService usuarioService;

  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<Usuario>> findAllUsuarios() {
    return new ResponseEntity<>(
      usuarioService.findAllUsuarios(),
      HttpStatus.OK
    );
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
    return new ResponseEntity<>(
      usuarioService.createUsuario(usuario),
      HttpStatus.CREATED
    );
  }
}

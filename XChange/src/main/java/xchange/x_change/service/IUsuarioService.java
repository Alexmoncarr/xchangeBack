package xchange.x_change.service;

import java.util.List;

import xchange.x_change.models.Usuario;

public interface IUsuarioService {
    List<Usuario> findAllUsuarios();

    Usuario createUsuario(Usuario usuario);
}

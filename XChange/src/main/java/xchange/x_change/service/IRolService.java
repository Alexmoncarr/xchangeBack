package xchange.x_change.service;

import java.util.List;

import xchange.x_change.models.Rol;

public interface IRolService {
    Rol createRol(Rol rol);

    List<Rol> findAllRoles();

    Rol updateRolById(Integer id, Rol rol);

    Rol findRolById(Integer id);

    void deleteRolById(Integer id);
}

package com.xchange.xchange.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xchange.xchange.models.Role;
import com.xchange.xchange.models.User;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findFirstByUserId(User user);

    List<Role> findAllByUserId(User user);

}

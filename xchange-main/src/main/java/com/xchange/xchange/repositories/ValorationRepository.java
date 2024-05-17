package com.xchange.xchange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xchange.xchange.models.User;
import com.xchange.xchange.models.Valoration;

public interface ValorationRepository extends JpaRepository<Valoration, Long> {

    Valoration findFirstByUserId(User user);

}

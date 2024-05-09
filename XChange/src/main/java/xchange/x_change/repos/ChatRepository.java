package xchange.x_change.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import xchange.x_change.domain.Chat;

public interface ChatRepository extends JpaRepository<Chat, Integer>{
    
}

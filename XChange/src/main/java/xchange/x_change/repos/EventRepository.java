package xchange.x_change.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import xchange.x_change.domain.Event;
import xchange.x_change.domain.User;


public interface EventRepository extends JpaRepository<Event, Integer> {
    Event findFirstByAdmin(User user);
}

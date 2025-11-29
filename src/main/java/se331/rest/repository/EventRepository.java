package se331.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.rest.entity.Event;

public interface EventRepository extends JpaRepository<Event,Long> {
}

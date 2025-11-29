package se331.rest.service;

import org.springframework.data.domain.Page;
import se331.rest.entity.Event;

public interface EventService {
    Integer getEventSize();
    Page<Event> getEvents(Integer pageSize, Integer page);
    Event getEvent(Long id);
    Event save(Event event);
    Event addEvent(Event event);
    Event updateEvent(Long id, Event event);
    void deleteEvent(Long id);
}
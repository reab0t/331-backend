package se331.rest.dao;

import se331.rest.entity.Event;

import java.util.List;

public interface EventDao {
    Integer getEventSize();
    List<Event> getEvents(Integer pageSize, Integer page);
    Event getEvent(Long id);
    Event save(Event event);
    Event update(Long id, Event event);
    void delete(Long id);
}
package se331.lab.dao;

import se331.lab.rest.entity.Event;

import java.util.List;

public interface EventDao {
    Integer getEventSize();
    List<Event> getEvents(Integer pageSize, Integer page);
    Event getEvent(Long id);
    Event save(Event event);
    Event update(Long id, Event event);
    void delete(Long id);
}
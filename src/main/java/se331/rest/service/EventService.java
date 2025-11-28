package se331.rest.service;

import se331.rest.entity.Event;

import java.util.List;

public interface EventService {
    Integer getEventSize();
    List<Event> getEvents(Integer pageSize, Integer page);
    Event getEvent(Long id);
    Event addEvent(Event event);
    Event updateEvent(Long id, Event event);
    void deleteEvent(Long id);
}
package se331.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se331.lab.dao.EventDao;
import se331.lab.rest.entity.Event;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    final EventDao eventDao;

    @Override
    public Integer getEventSize() {
        return eventDao.getEventSize();
    }

    @Override
    public List<Event> getEvents(Integer pageSize, Integer page) {
        return eventDao.getEvents(pageSize, page);
    }

    @Override
    public Event getEvent(Long id) {
        return eventDao.getEvent(id);
    }

    @Override
    public Event addEvent(Event event) {
        // 生成随机ID
        event.setId(new Random().nextLong(10000000) + 1000000);
        return eventDao.save(event);
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        return eventDao.update(id, event);
    }

    @Override
    public void deleteEvent(Long id) {
        eventDao.delete(id);
    }
}
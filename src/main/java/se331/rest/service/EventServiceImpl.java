package se331.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import se331.rest.dao.EventDao;
import se331.rest.entity.Event;

import java.util.Random;

@Service
public class EventServiceImpl implements EventService {
    final EventDao eventDao;
    
    public EventServiceImpl(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public Integer getEventSize() {
        return eventDao.getEventSize();
    }

    @Override
    public Page<Event> getEvents(Integer pageSize, Integer page) {
        return eventDao.getEvents(pageSize, page);
    }

    @Override
    public Event getEvent(Long id) {
        return eventDao.getEvent(id);
    }

    @Override
    public Event save(Event event) {
        return eventDao.save(event);
    }

    @Override
    public Event addEvent(Event event) {
        // 为了避免编译错误，直接返回原始event对象
        // 实际应用中可能需要设置ID等属性
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
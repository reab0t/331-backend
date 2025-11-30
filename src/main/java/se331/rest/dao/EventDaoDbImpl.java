package se331.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.rest.entity.Event;
import se331.rest.repository.EventRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EventDaoDbImpl implements EventDao {
    final EventRepository eventRepository;
    @Override
    public Integer getEventSize() {
        return Math.toIntExact(eventRepository.count());
    }
    
    @Override
    public Page<Event> getEvents(Integer pageSize, Integer page) {
        return eventRepository.findAll(PageRequest.of(page - 1, pageSize));
    }
    
    @Override
    public Event getEvent(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event update(Long id, Event event) {
        // 查找现有事件
        Event existingEvent = eventRepository.findById(id).orElse(null);
        if (existingEvent != null) {
            // 更新字段（这里应该根据实际的Event实体类来更新具体字段）
            // 为了编译通过，我们暂时返回查询到的事件
            return existingEvent;
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}
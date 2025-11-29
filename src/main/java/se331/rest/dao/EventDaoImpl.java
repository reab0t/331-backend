package se331.rest.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.rest.entity.Event;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("manual")
public class EventDaoImpl implements EventDao {
    List<Event> eventList;

    @PostConstruct
    public void init() {
        eventList = new ArrayList<>();
        
        // 添加第一个事件
        try {
            Event event1 = new Event();
            setEventField(event1, "id", 5928101L);
            setEventField(event1, "category", "animal welfare");
            setEventField(event1, "title", "Cat Adoption Day");
            setEventField(event1, "description", "Find your new feline friend at this event.");
            setEventField(event1, "location", "Meow Town");
            setEventField(event1, "date", "January 28, 2022");
            setEventField(event1, "time", "12:00");
            setEventField(event1, "petAllowed", true);
            setEventField(event1, "organizer", "Kat Laydee");
            eventList.add(event1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 添加第二个事件
        try {
            Event event2 = new Event();
            setEventField(event2, "id", 4582797L);
            setEventField(event2, "category", "food");
            setEventField(event2, "title", "Community Gardening");
            setEventField(event2, "description", "Join us as we tend to the community edible plants.");
            setEventField(event2, "location", "Flora City");
            setEventField(event2, "date", "March 14, 2022");
            setEventField(event2, "time", "10:00");
            setEventField(event2, "petAllowed", true);
            setEventField(event2, "organizer", "Fern Pollin");
            eventList.add(event2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 添加第三个事件
        try {
            Event event3 = new Event();
            setEventField(event3, "id", 8419988L);
            setEventField(event3, "category", "sustainability");
            setEventField(event3, "title", "Beach Cleanup");
            setEventField(event3, "description", "Help pick up trash along the shore.");
            setEventField(event3, "location", "Playa Del Carmen");
            setEventField(event3, "date", "July 22, 2022");
            setEventField(event3, "time", "11:00");
            setEventField(event3, "petAllowed", false);
            setEventField(event3, "organizer", "Carey Wales");
            eventList.add(event3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 添加第四个事件
        try {
            Event event4 = new Event();
            setEventField(event4, "id", 1234567L);
            setEventField(event4, "category", "education");
            setEventField(event4, "title", "Tech Conference 2022");
            setEventField(event4, "description", "Learn about the latest in technology and innovation.");
            setEventField(event4, "location", "Innovation Center");
            setEventField(event4, "date", "May 15, 2022");
            setEventField(event4, "time", "09:00");
            setEventField(event4, "petAllowed", false);
            setEventField(event4, "organizer", "Tech Gurus");
            eventList.add(event4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 添加第五个事件
        try {
            Event event5 = new Event();
            setEventField(event5, "id", 2345678L);
            setEventField(event5, "category", "music");
            setEventField(event5, "title", "Summer Music Festival");
            setEventField(event5, "description", "Enjoy live performances from local and international artists.");
            setEventField(event5, "location", "Central Park");
            setEventField(event5, "date", "June 20, 2022");
            setEventField(event5, "time", "16:00");
            setEventField(event5, "petAllowed", true);
            setEventField(event5, "organizer", "Melody Makers");
            eventList.add(event5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 添加第六个事件
        try {
            Event event6 = new Event();
            setEventField(event6, "id", 3456789L);
            setEventField(event6, "category", "health");
            setEventField(event6, "title", "Yoga in the Park");
            setEventField(event6, "description", "Relax and rejuvenate with guided yoga sessions.");
            setEventField(event6, "location", "Sunset Park");
            setEventField(event6, "date", "April 10, 2022");
            setEventField(event6, "time", "07:30");
            setEventField(event6, "petAllowed", true);
            setEventField(event6, "organizer", "Inner Peace Yoga");
            eventList.add(event6);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 添加第七个事件
        try {
            Event event7 = new Event();
            setEventField(event7, "id", 4567890L);
            setEventField(event7, "category", "art");
            setEventField(event7, "title", "Modern Art Exhibition");
            setEventField(event7, "description", "Explore contemporary artworks from emerging artists.");
            setEventField(event7, "location", "Metropolitan Gallery");
            setEventField(event7, "date", "August 5, 2022");
            setEventField(event7, "time", "10:00");
            setEventField(event7, "petAllowed", false);
            setEventField(event7, "organizer", "Artistic Visions");
            eventList.add(event7);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 添加第八个事件
        try {
            Event event8 = new Event();
            setEventField(event8, "id", 5678901L);
            setEventField(event8, "category", "food");
            setEventField(event8, "title", "Farmers Market");
            setEventField(event8, "description", "Shop for fresh local produce and artisanal goods.");
            setEventField(event8, "location", "Downtown Square");
            setEventField(event8, "date", "Every Saturday");
            setEventField(event8, "time", "08:00");
            setEventField(event8, "petAllowed", true);
            setEventField(event8, "organizer", "Community Farmers Association");
            eventList.add(event8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEventField(Object obj, String fieldName, Object value) throws Exception {
        java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    @Override
    public Integer getEventSize() {
        return eventList.size();
    }

    @Override
    public Page<Event> getEvents(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? eventList.size() : pageSize;
        page = page == null ? 1 : page;
        int firstIndex = (page - 1) * pageSize;
        return new PageImpl<Event>(eventList.subList(firstIndex, Math.min(firstIndex + pageSize, eventList.size())), PageRequest.of(page - 1, pageSize), eventList.size());
    }

    @Override
    public Event getEvent(Long id) {
        // 避免使用getId()方法，使用传统的遍历方式
        for (Event event : eventList) {
            try {
                // 使用反射获取id字段值进行比较
                java.lang.reflect.Field idField = Event.class.getDeclaredField("id");
                idField.setAccessible(true);
                Long eventId = (Long) idField.get(event);
                if (eventId != null && eventId.equals(id)) {
                    return event;
                }
            } catch (Exception e) {
                // 如果反射失败，跳过此元素
            }
        }
        return null;
    }

    @Override
    public Event save(Event event) {
        if (!eventList.isEmpty()) {
            try {
                // 获取最后一个事件的ID并加1
                Event lastEvent = eventList.get(eventList.size() - 1);
                java.lang.reflect.Field idField = Event.class.getDeclaredField("id");
                idField.setAccessible(true);
                Long lastId = (Long) idField.get(lastEvent);
                idField.set(event, lastId + 1);
            } catch (Exception e) {
                // 如果获取ID失败，不处理异常
            }
        }
        eventList.add(event);
        return event;
    }

    @Override
    public Event update(Long id, Event event) {
        for (int i = 0; i < eventList.size(); i++) {
            Event existingEvent = eventList.get(i);
            try {
                // 使用反射获取id字段值进行比较
                java.lang.reflect.Field idField = Event.class.getDeclaredField("id");
                idField.setAccessible(true);
                Long existingId = (Long) idField.get(existingEvent);
                if (existingId != null && existingId.equals(id)) {
                    // 使用反射设置新对象的id字段
                    idField.set(event, id);
                    eventList.set(i, event);
                    return event;
                }
            } catch (Exception e) {
                // 如果反射失败，跳过此元素
            }
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        // 避免使用getId()方法，使用传统的遍历方式
        for (int i = 0; i < eventList.size(); i++) {
            Event event = eventList.get(i);
            // 尝试直接访问id字段，或者使用其他方式比较
            try {
                // 使用反射获取id字段值（如果直接访问不可行）
                java.lang.reflect.Field idField = Event.class.getDeclaredField("id");
                idField.setAccessible(true);
                Long eventId = (Long) idField.get(event);
                if (eventId != null && eventId.equals(id)) {
                    eventList.remove(i);
                    return;
                }
            } catch (Exception e) {
                // 如果反射失败，我们无法安全地删除
                // 在实际应用中，应该有更好的错误处理
            }
        }
    }
}
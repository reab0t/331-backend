package se331.rest.dao;

import org.springframework.stereotype.Repository;
import se331.rest.entity.Event;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventDaoImpl implements EventDao {
    List<Event> eventList;

    @PostConstruct
    public void init() {
        eventList = new ArrayList<>();
        
        // 添加第一个事件
        eventList.add(Event.builder()
                .id(5928101L)
                .category("animal welfare")
                .title("Cat Adoption Day")
                .description("Find your new feline friend at this event.")
                .location("Meow Town")
                .date("January 28, 2022")
                .time("12:00")
                .petAllowed(true)
                .organizer("Kat Laydee")
                .build());
        
        // 添加第二个事件
        eventList.add(Event.builder()
                .id(4582797L)
                .category("food")
                .title("Community Gardening")
                .description("Join us as we tend to the community edible plants.")
                .location("Flora City")
                .date("March 14, 2022")
                .time("10:00")
                .petAllowed(true)
                .organizer("Fern Pollin")
                .build());
        
        // 添加第三个事件
        eventList.add(Event.builder()
                .id(8419988L)
                .category("sustainability")
                .title("Beach Cleanup")
                .description("Help pick up trash along the shore.")
                .location("Playa Del Carmen")
                .date("July 22, 2022")
                .time("11:00")
                .petAllowed(false)
                .organizer("Carey Wales")
                .build());
        
        // 添加第四个事件
        eventList.add(Event.builder()
                .id(1234567L)
                .category("education")
                .title("Tech Conference 2022")
                .description("Learn about the latest in technology and innovation.")
                .location("Innovation Center")
                .date("May 15, 2022")
                .time("09:00")
                .petAllowed(false)
                .organizer("Tech Gurus")
                .build());
        
        // 添加第五个事件
        eventList.add(Event.builder()
                .id(2345678L)
                .category("music")
                .title("Summer Music Festival")
                .description("Enjoy live performances from local and international artists.")
                .location("Central Park")
                .date("June 20, 2022")
                .time("16:00")
                .petAllowed(true)
                .organizer("Melody Makers")
                .build());
        
        // 添加第六个事件
        eventList.add(Event.builder()
                .id(3456789L)
                .category("health")
                .title("Yoga in the Park")
                .description("Relax and rejuvenate with guided yoga sessions.")
                .location("Sunset Park")
                .date("April 10, 2022")
                .time("07:30")
                .petAllowed(true)
                .organizer("Inner Peace Yoga")
                .build());
        
        // 添加第七个事件
        eventList.add(Event.builder()
                .id(4567890L)
                .category("art")
                .title("Modern Art Exhibition")
                .description("Explore contemporary artworks from emerging artists.")
                .location("Metropolitan Gallery")
                .date("August 5, 2022")
                .time("10:00")
                .petAllowed(false)
                .organizer("Artistic Visions")
                .build());
        
        // 添加第八个事件
        eventList.add(Event.builder()
                .id(5678901L)
                .category("food")
                .title("Farmers Market")
                .description("Shop for fresh local produce and artisanal goods.")
                .location("Downtown Square")
                .date("Every Saturday")
                .time("08:00")
                .petAllowed(true)
                .organizer("Community Farmers Association")
                .build());
    }

    @Override
    public Integer getEventSize() {
        return eventList.size();
    }

    @Override
    public List<Event> getEvents(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? eventList.size() : pageSize;
        page = page == null ? 1 : page;
        int firstIndex = (page - 1) * pageSize;
        return eventList.subList(firstIndex, Math.min(firstIndex + pageSize, eventList.size()));
    }

    @Override
    public Event getEvent(Long id) {
        return eventList.stream().filter(event -> event.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Event save(Event event) {
        eventList.add(event);
        return event;
    }

    @Override
    public Event update(Long id, Event event) {
        for (int i = 0; i < eventList.size(); i++) {
            if (eventList.get(i).getId().equals(id)) {
                // 保留原ID，更新其他字段
                event.setId(id);
                eventList.set(i, event);
                return event;
            }
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        eventList.removeIf(event -> event.getId().equals(id));
    }
}
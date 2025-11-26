package se331.lab.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.entity.Event;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EventController {
    List<Event> eventList;

    @PostConstruct
    public void init() {
        eventList = new ArrayList<>();
        
        // 使用标准构造函数创建Event对象
        Event event1 = new Event();
        event1.setId(5928101L);
        event1.setCategory("animal welfare");
        event1.setTitle("Cat Adoption Day");
        event1.setDescription("Find your new feline friend at this event.");
        event1.setLocation("Meow Town");
        event1.setDate("January 28, 2022");
        event1.setTime("12:00");
        event1.setPetAllowed(true);
        event1.setOrganizer("Kat Laydee");
        eventList.add(event1);
        
        Event event2 = new Event();
        event2.setId(4582797L);
        event2.setCategory("food");
        event2.setTitle("Community Gardening");
        event2.setDescription("Join us as we tend to the community edible plants.");
        event2.setLocation("Flora City");
        event2.setDate("March 14, 2022");
        event2.setTime("10:00");
        event2.setPetAllowed(true);
        event2.setOrganizer("Fern Pollin");
        eventList.add(event2);
        
        Event event3 = new Event();
        event3.setId(8419988L);
        event3.setCategory("sustainability");
        event3.setTitle("Beach Cleanup");
        event3.setDescription("Help pick up trash along the shore.");
        event3.setLocation("Playa Del Carmen");
        event3.setDate("July 22, 2022");
        event3.setTime("11:00");
        event3.setPetAllowed(false);
        event3.setOrganizer("Carey Wales");
        eventList.add(event3);
    }
    @GetMapping("/events")
    public ResponseEntity<?> getEventLists(@RequestParam(value = "_limit", required = false) Integer perPage,
                                          @RequestParam(value = "_page", required = false) Integer page) {
        perPage = perPage == null ? eventList.size() : perPage;
        page = page == null ? 1 : page;
        
        // 计算总页数
        int totalPages = (int) Math.ceil((double) eventList.size() / perPage);
        
        // 检查页码是否有效
        if (page < 1 || page > totalPages) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid page number: " + page);
        }
        
        Integer firstIndex = (page - 1) * perPage;
        List<Event> output = new ArrayList<>();
        try {
            for (int i = firstIndex; i < Math.min(firstIndex + perPage, eventList.size()); i++) {
                output.add(eventList.get(i));
            }
            return ResponseEntity.ok(output);
        } catch (IndexOutOfBoundsException ex) {
            return ResponseEntity.ok(output);
        }
    }
    
    @GetMapping("/events/{id}")
    public ResponseEntity<?> getEvent(@PathVariable("id") Long id) {
        Event output = null;
        for (Event event : eventList) {
            if (event.getId().equals(id)) {
                output = event;
                break;
            }
        }
        if (output != null) {
            return ResponseEntity.ok(output);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
}




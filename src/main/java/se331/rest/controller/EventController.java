package se331.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.rest.entity.Event;
import se331.rest.entity.EventDTO;
import se331.rest.service.EventService;
import se331.rest.util.LabMapper;

@RestController
@RequestMapping("/api")
public class EventController {
    final EventService eventService;
    final LabMapper labMapper;
    
    @Autowired
    public EventController(EventService eventService, LabMapper labMapper) {
        this.eventService = eventService;
        this.labMapper = labMapper;
    }
    @GetMapping("/events")
    public ResponseEntity<?> getEventLists(@RequestParam(value = "_limit", required = false) Integer perPage,
                                          @RequestParam(value = "_page", required = false) Integer page) {
        Page<Event> pageOutput = eventService.getEvents(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(labMapper.getEventDtoList(pageOutput.getContent()), responseHeader, HttpStatus.OK);
    }
    
    @GetMapping("/events/{id}")
    public ResponseEntity<?> getEvent(@PathVariable("id") Long id) {
        Event output = eventService.getEvent(id);
        if (output != null) {
            return ResponseEntity.ok(labMapper.getEventDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
    
    @PostMapping("/events")
    public ResponseEntity<?> addEvent(@RequestBody EventDTO eventDto) {
        // 从DTO创建新的Event实体
        Event event = Event.builder()
                .category(eventDto.getCategory())
                .title(eventDto.getTitle())
                .description(eventDto.getDescription())
                .location(eventDto.getLocation())
                .date(eventDto.getDate())
                .time(eventDto.getTime())
                .petAllowed(eventDto.getPetAllowed())
                .build();
        
        // 版本字段会使用默认值0L
        Event output = eventService.save(event);
        return ResponseEntity.ok(labMapper.getEventDto(output));
    }
    
    @PutMapping("/events/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable("id") Long id, @RequestBody EventDTO eventDto) {
        // 先获取现有事件
        Event existingEvent = eventService.getEvent(id);
        if (existingEvent == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
        
        // 更新字段但保留version等ORM管理的字段
        existingEvent.setCategory(eventDto.getCategory());
        existingEvent.setTitle(eventDto.getTitle());
        existingEvent.setDescription(eventDto.getDescription());
        existingEvent.setLocation(eventDto.getLocation());
        existingEvent.setDate(eventDto.getDate());
        existingEvent.setTime(eventDto.getTime());
        existingEvent.setPetAllowed(eventDto.getPetAllowed());
        
        // 保存更新后的事件
        Event output = eventService.save(existingEvent);
        return ResponseEntity.ok(labMapper.getEventDto(output));
    }
    
    @DeleteMapping("/events/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") Long id) {
        Event existing = eventService.getEvent(id);
        if (existing != null) {
            eventService.deleteEvent(id);
            return ResponseEntity.ok().build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
    }
}




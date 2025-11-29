package se331.rest;

import jakarta.transaction.Transactional;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.rest.entity.Event;
import se331.rest.entity.Organizer;
import se331.rest.repository.EventRepository;
import se331.rest.repository.OrganizerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final EventRepository eventRepository;
    final OrganizerRepository organizerRepository;
    
    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        // 检查是否已有数据，如果有则不再初始化
        if (eventRepository.count() == 0) {
            log.info("Initializing database with event data...");
            
            // 创建组织者
            Organizer org1, org2, org3;
            org1 = organizerRepository.save(Organizer.builder().name("CAMT").build());
            org2 = organizerRepository.save(Organizer.builder().name("CMU").build());
            org3 = organizerRepository.save(Organizer.builder().name("ChiangMai").build());
            log.info("Saved organizers: {}, {}, {}", org1.getName(), org2.getName(), org3.getName());
            
            // 创建并关联事件与组织者
            Event tempEvent;
            
            tempEvent = eventRepository.save(Event.builder()
                    .category("Academic")
                    .title("Midterm Exam")
                    .description("A time for taking the exam")
                    .location("CAMT Building")
                    .date("3rd Sept")
                    .time("3.00-4.00 pm.")
                    .petAllowed(false)
                    .build());
            tempEvent.setOrganizer(org1);
            org1.getOwnEvents().add(tempEvent);
            eventRepository.save(tempEvent);
            log.info("Saved event 1: {} with organizer: {}", tempEvent.getTitle(), org1.getName());
            
            tempEvent = eventRepository.save(Event.builder()
                    .category("Academic")
                    .title("Commencement Day")
                    .description("A time for celebration")
                    .location("CMU Convention hall")
                    .date("21th Jan")
                    .time("8.00am-4.00 pm.")
                    .petAllowed(false)
                    .build());
            tempEvent.setOrganizer(org1);
            org1.getOwnEvents().add(tempEvent);
            eventRepository.save(tempEvent);
            log.info("Saved event 2: {} with organizer: {}", tempEvent.getTitle(), org1.getName());
            
            tempEvent = eventRepository.save(Event.builder()
                    .category("Cultural")
                    .title("Loy Krathong")
                    .description("A time for Krathong")
                    .location("Ping River")
                    .date("21th Nov")
                    .time("8.00-10.00 pm.")
                    .petAllowed(false)
                    .build());
            tempEvent.setOrganizer(org2);
            org2.getOwnEvents().add(tempEvent);
            eventRepository.save(tempEvent);
            log.info("Saved event 3: {} with organizer: {}", tempEvent.getTitle(), org2.getName());
            
            tempEvent = eventRepository.save(Event.builder()
                    .category("Cultural")
                    .title("Songkran")
                    .description("Let's Play Water")
                    .location("Chiang Mai Moat")
                    .date("13th April")
                    .time("10.00am - 6.00 pm.")
                    .petAllowed(true)
                    .build());
            tempEvent.setOrganizer(org3);
            org3.getOwnEvents().add(tempEvent);
            eventRepository.save(tempEvent);
            log.info("Saved event 4: {} with organizer: {}", tempEvent.getTitle(), org3.getName());
            
            log.info("Database initialization completed. Total events: {}, Total organizers: {}", 
                    eventRepository.count(), organizerRepository.count());
        } else {
            log.info("Database already contains data. Skipping initialization.");
        }
    }
}
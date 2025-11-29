package se331.rest;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.rest.entity.Event;
import se331.rest.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final EventRepository eventRepository;
    
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        // 检查是否已有数据，如果有则不再初始化
        if (eventRepository.count() == 0) {
            log.info("Initializing database with event data...");
            
            // 使用Builder模式创建Event对象
            Event event1 = Event.builder()
                    .category("Academic")
                    .title("Midterm Exam")
                    .description("A time for taking the exam")
                    .location("CAMT Building")
                    .date("3rd Sept")
                    .time("3.00-4.00 pm.")
                    .petAllowed(false)
                    .organizer("CAMT")
                    .build();
            eventRepository.save(event1);
            log.info("Saved event 1: {}", event1.getTitle());
            
            Event event2 = Event.builder()
                    .category("Academic")
                    .title("Commencement Day")
                    .description("A time for celebration")
                    .location("CMU Convention hall")
                    .date("21th Jan")
                    .time("8.00am-4.00 pm.")
                    .petAllowed(false)
                    .organizer("CMU")
                    .build();
            eventRepository.save(event2);
            log.info("Saved event 2: {}", event2.getTitle());
            
            Event event3 = Event.builder()
                    .category("Cultural")
                    .title("Loy Krathong")
                    .description("A time for Krathong")
                    .location("Ping River")
                    .date("21th Nov")
                    .time("8.00-10.00 pm.")
                    .petAllowed(false)
                    .organizer("Chiang Mai")
                    .build();
            eventRepository.save(event3);
            log.info("Saved event 3: {}", event3.getTitle());
            
            Event event4 = Event.builder()
                    .category("Cultural")
                    .title("Songkran")
                    .description("Let's Play Water")
                    .location("Chiang Mai Moat")
                    .date("13th April")
                    .time("10.00am - 6.00 pm.")
                    .petAllowed(true)
                    .organizer("Chiang Mai Municipality")
                    .build();
            eventRepository.save(event4);
            log.info("Saved event 4: {}", event4.getTitle());
            
            log.info("Database initialization completed. Total events: {}", eventRepository.count());
        } else {
            log.info("Database already contains data. Skipping initialization.");
        }
    }
}
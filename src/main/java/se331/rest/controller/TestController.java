package se331.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se331.rest.dao.EventDao;

@RestController
@RequestMapping("/api/test")
public class TestController {
    
    @Autowired
    private EventDao eventDao;
    
    @GetMapping("/eventdao")
    public ResponseEntity<String> testEventDao() {
        try {
            if (eventDao != null) {
                return ResponseEntity.ok("EventDao bean injected successfully!");
            } else {
                return ResponseEntity.badRequest().body("EventDao bean is null!");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
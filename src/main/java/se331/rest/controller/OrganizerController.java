package se331.rest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.rest.entity.Organizer;
import se331.rest.service.OrganizerService;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrganizerController {
    final OrganizerService organizerService;
    
    @GetMapping("/organizers")
    public ResponseEntity<?> getOrganizerLists(@RequestParam(value = "_limit", required = false) Integer perPage,
                                             @RequestParam(value = "_page", required = false) Integer page) {
        List<Organizer> output = null;
        Integer organizerSize = organizerService.getOrganizerSize();
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(organizerSize));
        try {
            output = organizerService.getOrganizers(perPage, page);
            return new ResponseEntity<>(output, responseHeader, HttpStatus.OK);
        } catch (IndexOutOfBoundsException ex) {
            return new ResponseEntity<>(output, responseHeader, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/organizers/{id}")
    public ResponseEntity<?> getOrganizer(@PathVariable("id") Long id) {
        Organizer output = organizerService.getOrganizer(id);
        if (output != null) {
            return ResponseEntity.ok(output);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
    
    @PostMapping("/organizers")
    public ResponseEntity<?> addOrganizer(@RequestBody Organizer organizer) {
        Organizer added = organizerService.addOrganizer(organizer);
        return ResponseEntity.status(HttpStatus.CREATED).body(added);
    }
    
    @PutMapping("/organizers/{id}")
    public ResponseEntity<?> updateOrganizer(@PathVariable("id") Long id, @RequestBody Organizer updatedOrganizer) {
        Organizer output = organizerService.updateOrganizer(id, updatedOrganizer);
        if (output != null) {
            return ResponseEntity.ok(output);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizer not found");
        }
    }
    
    @DeleteMapping("/organizers/{id}")
    public ResponseEntity<?> deleteOrganizer(@PathVariable("id") Long id) {
        Organizer existing = organizerService.getOrganizer(id);
        if (existing != null) {
            organizerService.deleteOrganizer(id);
            return ResponseEntity.ok().build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizer not found");
        }
    }
}
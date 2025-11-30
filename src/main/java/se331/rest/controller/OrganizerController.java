package se331.rest.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.rest.entity.Organizer;
import se331.rest.entity.OrganizerDTO;
import se331.rest.service.OrganizerService;
import se331.rest.util.LabMapper;

@RestController
@RequestMapping("/api/organizers")
@RequiredArgsConstructor
public class OrganizerController {
    final OrganizerService organizerService;
    final LabMapper labMapper;
    
    @GetMapping
    ResponseEntity<?> getOrganizations(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page) {
        // 支持默认值和多种参数名
        int pageNum = page != null ? page - 1 : 0;
        int size = perPage != null ? perPage : 10;
        
        Page<Organizer> pageOutput = organizerService.getOrganizer(pageNum, size);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(labMapper.getOrganizerDTOList(pageOutput.getContent()), responseHeader, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    ResponseEntity<?> getOrganization(@PathVariable("id") Long id) {
        Organizer organizer = organizerService.getOrganizerById(id);
        if (organizer != null) {
            return ResponseEntity.ok(labMapper.getOrganizerDTO(organizer));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizer not found");
        }
    }
    
    @PostMapping
    ResponseEntity<?> saveOrganization(@RequestBody Organizer organizer) {
        Organizer saved = organizerService.save(organizer);
        return ResponseEntity.ok(labMapper.getOrganizerDTO(saved));
    }
    
    @PutMapping("/{id}")
    ResponseEntity<?> updateOrganization(@PathVariable("id") Long id, @RequestBody Organizer organizer) {
        Organizer updated = organizerService.updateOrganizer(id, organizer);
        if (updated != null) {
            return ResponseEntity.ok(labMapper.getOrganizerDTO(updated));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizer not found");
        }
    }
    
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteOrganization(@PathVariable("id") Long id) {
        Organizer existing = organizerService.getOrganizerById(id);
        if (existing != null) {
            organizerService.deleteOrganizer(id);
            return ResponseEntity.ok().build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizer not found");
        }
    }
}
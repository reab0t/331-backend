package se331.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se331.rest.entity.Organization;
import se331.rest.service.OrganizationService;

@RestController
@RequestMapping("/api")
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @GetMapping("/organizations")
    public ResponseEntity<?> getOrganizations(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Page<Organization> pageOutput = organizationService.getOrganizations(pageSize, page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(pageOutput.getContent(), responseHeader, HttpStatus.OK);
    }

    @GetMapping("/organizations/{id}")
    public ResponseEntity<?> getOrganization(@PathVariable("id") Long id) {
        Organization output = organizationService.getOrganization(id);
        if (output != null) {
            return ResponseEntity.ok(output);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/organizations")
    public ResponseEntity<?> addOrganization(@RequestBody Organization organization) {
        Organization createdOrganization = organizationService.save(organization);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrganization);
    }

    @PutMapping("/organizations/{id}")
    public ResponseEntity<?> updateOrganization(@PathVariable("id") Long id, @RequestBody Organization organization) {
        Organization updatedOrganization = organizationService.update(id, organization);
        if (updatedOrganization != null) {
            return ResponseEntity.ok(updatedOrganization);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/organizations/{id}")
    public ResponseEntity<?> deleteOrganization(@PathVariable("id") Long id) {
        organizationService.delete(id);
        return ResponseEntity.ok().build();
    }
}
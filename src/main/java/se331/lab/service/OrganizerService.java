package se331.lab.service;

import se331.lab.rest.entity.Organizer;

import java.util.List;

public interface OrganizerService {
    Integer getOrganizerSize();
    List<Organizer> getOrganizers(Integer pageSize, Integer page);
    Organizer getOrganizer(Long id);
    Organizer addOrganizer(Organizer organizer);
    Organizer updateOrganizer(Long id, Organizer organizer);
    void deleteOrganizer(Long id);
}
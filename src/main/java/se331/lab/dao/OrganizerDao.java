package se331.lab.dao;

import se331.lab.rest.entity.Organizer;

import java.util.List;

public interface OrganizerDao {
    Integer getOrganizerSize();
    List<Organizer> getOrganizers(Integer pageSize, Integer page);
    Organizer getOrganizer(Long id);
    Organizer save(Organizer organizer);
    Organizer update(Long id, Organizer organizer);
    void delete(Long id);
}
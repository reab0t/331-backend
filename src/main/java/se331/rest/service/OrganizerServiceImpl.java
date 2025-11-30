package se331.rest.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.rest.dao.OrganizerDao;
import se331.rest.entity.Organizer;
import se331.rest.repository.OrganizerRepository;

@Service
@RequiredArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {
    final OrganizerDao organizerDao;
    final OrganizerRepository organizerRepository;
    
    @Override
    public List<Organizer> getAllOrganizer() {
        return organizerDao.getOrganizer(Pageable.unpaged()).getContent();
    }
    
    @Override
    public Page<Organizer> getOrganizer(Integer page, Integer pageSize) {
        return organizerDao.getOrganizer(PageRequest.of(page, pageSize));
    }
    
    @Override
    public Organizer getOrganizerById(Long id) {
        return organizerRepository.findById(id).orElse(null);
    }
    
    @Override
    public Organizer save(Organizer organizer) {
        return organizerRepository.save(organizer);
    }
    
    @Override
    public Organizer updateOrganizer(Long id, Organizer organizer) {
        Optional<Organizer> existing = organizerRepository.findById(id);
        if (existing.isPresent()) {
            organizer.setId(id);
            return organizerRepository.save(organizer);
        }
        return null;
    }
    
    @Override
    public void deleteOrganizer(Long id) {
        organizerRepository.deleteById(id);
    }
}
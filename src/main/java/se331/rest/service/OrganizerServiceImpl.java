package se331.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se331.rest.dao.OrganizerDao;
import se331.rest.entity.Organizer;

import java.util.List;
import java.util.Random;

@Service
public class OrganizerServiceImpl implements OrganizerService {
    final OrganizerDao organizerDao;
    
    public OrganizerServiceImpl(OrganizerDao organizerDao) {
        this.organizerDao = organizerDao;
    }

    @Override
    public Integer getOrganizerSize() {
        return organizerDao.getOrganizerSize();
    }

    @Override
    public List<Organizer> getOrganizers(Integer pageSize, Integer page) {
        return organizerDao.getOrganizers(pageSize, page);
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return organizerDao.getOrganizer(id);
    }

    @Override
    public Organizer addOrganizer(Organizer organizer) {
        // 生成随机ID并使用反射设置
        try {
            long randomId = new Random().nextLong(10000000) + 1000000;
            java.lang.reflect.Field idField = organizer.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(organizer, randomId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return organizerDao.save(organizer);
    }

    @Override
    public Organizer updateOrganizer(Long id, Organizer organizer) {
        return organizerDao.update(id, organizer);
    }

    @Override
    public void deleteOrganizer(Long id) {
        organizerDao.delete(id);
    }
}
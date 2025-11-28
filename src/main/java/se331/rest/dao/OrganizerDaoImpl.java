package se331.rest.dao;

import org.springframework.stereotype.Repository;
import se331.rest.entity.Organizer;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizerDaoImpl implements OrganizerDao {
    List<Organizer> organizerList;

    @PostConstruct
    public void init() {
        organizerList = new ArrayList<>();
        
        // 添加一些样本数据
        organizerList.add(new Organizer(1L, "Tech Gurus", "123 Tech Avenue, Bangkok"));
        organizerList.add(new Organizer(2L, "Melody Makers", "456 Music Street, Chiang Mai"));
        organizerList.add(new Organizer(3L, "Inner Peace Yoga", "789 Wellness Road, Phuket"));
        organizerList.add(new Organizer(4L, "Artistic Visions", "101 Art Gallery, Pattaya"));
        organizerList.add(new Organizer(5L, "Community Farmers Association", "202 Countryside Lane, Korat"));
    }

    @Override
    public Integer getOrganizerSize() {
        return organizerList.size();
    }

    @Override
    public List<Organizer> getOrganizers(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? organizerList.size() : pageSize;
        page = page == null ? 1 : page;
        int firstIndex = (page - 1) * pageSize;
        return organizerList.subList(firstIndex, Math.min(firstIndex + pageSize, organizerList.size()));
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return organizerList.stream().filter(organizer -> organizer.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Organizer save(Organizer organizer) {
        organizerList.add(organizer);
        return organizer;
    }

    @Override
    public Organizer update(Long id, Organizer organizer) {
        for (int i = 0; i < organizerList.size(); i++) {
            if (organizerList.get(i).getId().equals(id)) {
                // 保留原ID，更新其他字段
                organizer.setId(id);
                organizerList.set(i, organizer);
                return organizer;
            }
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        organizerList.removeIf(organizer -> organizer.getId().equals(id));
    }
}
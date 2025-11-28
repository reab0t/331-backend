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
        organizerList.add(Organizer.builder()
                .id(1L)
                .organizationName("Tech Gurus")
                .address("123 Tech Avenue, Bangkok")
                .build());
        
        organizerList.add(Organizer.builder()
                .id(2L)
                .organizationName("Melody Makers")
                .address("456 Music Street, Chiang Mai")
                .build());
        
        organizerList.add(Organizer.builder()
                .id(3L)
                .organizationName("Inner Peace Yoga")
                .address("789 Wellness Road, Phuket")
                .build());
        
        organizerList.add(Organizer.builder()
                .id(4L)
                .organizationName("Artistic Visions")
                .address("101 Art Gallery, Pattaya")
                .build());
        
        organizerList.add(Organizer.builder()
                .id(5L)
                .organizationName("Community Farmers Association")
                .address("202 Countryside Lane, Korat")
                .build());
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
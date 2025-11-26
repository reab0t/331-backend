package se331.lab.rest.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Organizer {
    Long id;
    String organizationName;
    String address;
    
    // 手动添加构造函数
    public Organizer() {
    }
    
    public Organizer(Long id, String organizationName, String address) {
        this.id = id;
        this.organizationName = organizationName;
        this.address = address;
    }
    
    // 手动添加getter方法
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getOrganizationName() {
        return organizationName;
    }
    
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
}
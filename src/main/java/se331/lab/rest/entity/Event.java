package se331.lab.rest.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Event {
    Long id;
    String category;
    String title;
    String description;
    String location;
    String date;
    String time;
    Boolean petAllowed;
    String organizer;
    
    // 手动添加构造函数
    public Event() {
    }
    
    public Event(Long id, String category, String title, String description, String location, 
                 String date, String time, Boolean petAllowed, String organizer) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
        this.time = time;
        this.petAllowed = petAllowed;
        this.organizer = organizer;
    }
    
    // 手动添加getter方法
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getTime() {
        return time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public Boolean getPetAllowed() {
        return petAllowed;
    }
    
    public void setPetAllowed(Boolean petAllowed) {
        this.petAllowed = petAllowed;
    }
    
    public String getOrganizer() {
        return organizer;
    }
    
    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }
}

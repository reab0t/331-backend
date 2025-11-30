package se331.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import se331.rest.entity.Organizer;
import se331.rest.entity.Participant;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String category;
    String title;
    String description;
    String location;
    String date;
    String time;
    Boolean petAllowed;
    
    @Version
    @EqualsAndHashCode.Exclude
    @Builder.Default
    Long version = 0L;
    
    @ManyToOne
    @JsonIgnore
    Organizer organizer;
    
    @ManyToMany
    @Builder.Default
    List<Participant> participants = new ArrayList<>();
}

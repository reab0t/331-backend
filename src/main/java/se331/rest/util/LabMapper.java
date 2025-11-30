package se331.rest.util;

import org.mapstruct.Mapper;
import se331.rest.entity.Event;
import se331.rest.entity.EventDTO;
import se331.rest.entity.Organizer;
import se331.rest.entity.OrganizerDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LabMapper {
    EventDTO getEventDto(Event event);
    List<EventDTO> getEventDtoList(List<Event> events);
    OrganizerDTO getOrganizerDTO(Organizer organizer);
    List<OrganizerDTO> getOrganizerDTOList(List<Organizer> organizers);
}

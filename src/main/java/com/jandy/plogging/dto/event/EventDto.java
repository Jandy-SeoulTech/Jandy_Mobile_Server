package com.jandy.plogging.dto.event;

import com.jandy.plogging.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class EventDto {

    private String name;

    private String host;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate applicationStartDate;

    private LocalDate applicationEndDate;

    private String location;

    private String cost;

    private String description;

    private String eventLocation;

    private String eventAddress;

    private String homepage;

    public static EventDto fromAll(Event event) {
        return new EventDto(event.getName(), event.getHost(),
                event.getStartDate(), event.getEndDate(),
                event.getApplicationStartDate(), event.getApplicationEndDate(),
                event.getLocation(), event.getCost(),
                event.getDescription(), event.getEventLocation(),
                event.getEventAddress(), event.getHomepage());
    }
}

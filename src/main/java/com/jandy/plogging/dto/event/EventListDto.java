package com.jandy.plogging.dto.event;

import com.jandy.plogging.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class EventListDto {

    private Long id;

    private String name;

    private String host;

    private LocalDate applicationStartDate;

    private LocalDate applicationEndDate;

    public static EventListDto from(Event event) {
        return new EventListDto(event.getId(), event.getName(), event.getHost(), event.getApplicationStartDate(), event.getApplicationEndDate());
    }
}

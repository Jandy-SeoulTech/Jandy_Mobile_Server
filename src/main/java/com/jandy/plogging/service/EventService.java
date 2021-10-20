package com.jandy.plogging.service;

import com.jandy.plogging.domain.Event;
import com.jandy.plogging.dto.ListResponse;
import com.jandy.plogging.dto.event.EventDto;
import com.jandy.plogging.dto.event.EventListDto;
import com.jandy.plogging.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public ListResponse getEventList() {
        List<Event> eventList = eventRepository.findAll();
        List<EventListDto> collect = eventList.stream()
                .map(EventListDto::from)
                .collect(toList());

        return new ListResponse(collect.size(), collect);
    }

    public EventDto getEventOne(Long eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        Event event = eventOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 이벤트입니다."));
        return EventDto.fromAll(event);
    }

}

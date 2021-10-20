package com.jandy.plogging.controller;

import com.jandy.plogging.dto.ListResponse;
import com.jandy.plogging.dto.event.EventDto;
import com.jandy.plogging.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<ListResponse> getEventList() {
        return ResponseEntity.ok()
                .body(eventService.getEventList());
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDto> getEventOne(@PathVariable Long eventId) {
        return ResponseEntity.ok()
                .body(eventService.getEventOne(eventId));
    }


}

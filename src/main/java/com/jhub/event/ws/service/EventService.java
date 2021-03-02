package com.jhub.event.ws.service;

import java.util.List;

import com.jhub.event.shated.dto.EventDto;

public interface EventService {

	List<EventDto> getAllEvent();

	EventDto getEventById(Long id);

	EventDto createEvent(EventDto eventDto);

	EventDto updateEvent(Long id, EventDto eventDto);

	Boolean deleteEventById(Long id);

	Boolean hardDeleteEventById(Long id);

}

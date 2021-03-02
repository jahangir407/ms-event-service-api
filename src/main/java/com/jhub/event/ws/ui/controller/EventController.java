package com.jhub.event.ws.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhub.event.shated.dto.EventDto;
import com.jhub.event.ws.service.EventService;

@RestController
@RequestMapping(path = "/event")
public class EventController {

	@Autowired
	EventService eventService;

	@GetMapping
	List<EventDto> getAllEvent() {
		return this.eventService.getAllEvent();
	}

	@GetMapping(path = "/{id}")
	EventDto getEventById(@PathVariable("id") Long id) {
		return this.eventService.getEventById(id);
	}

	@PostMapping
	EventDto createEvent(@RequestBody EventDto eventDto) {
		return this.eventService.createEvent(eventDto);
	}

	@PutMapping(path = "/{id}")
	EventDto updateEvent(@PathVariable("id") Long id, @RequestBody EventDto eventDto) {
		return this.eventService.updateEvent(id, eventDto);

	}
	
	@DeleteMapping(path = "/{id}")
	Boolean deleteEventById(@PathVariable("id") Long id) {
		return this.deleteEventById(id);
	}
	
	@DeleteMapping(path = "/hardDelete/{id}")
	Boolean hardDeleteEventById(@PathVariable("id") Long id) {
		return this.hardDeleteEventById(id);
	}

}

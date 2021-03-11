package com.jhub.event.ws.ui.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.coyote.http11.Http11AprProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/event", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class EventController {

	@Autowired
	EventService eventService;

	@GetMapping
	List<EventDto> getAllEvent() {
		log.trace("A TRACE Message");
		log.debug("A DEBUG Message");
		log.info("An INFO Message");
		log.warn("A WARN Message");
		log.error("An ERROR Message");

		return this.eventService.getAllEvent();
	}

	@GetMapping(path = "/{id}")
	EventDto getEventById(@PathVariable("id") Long id) {
		return this.eventService.getEventById(id);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<EventDto> createEvent(@Valid @RequestBody EventDto eventDto) {
		EventDto savedEvent = this.eventService.createEvent(eventDto);
		return ResponseEntity.ok().body(savedEvent);
	}

	@PutMapping(path = "/{id}")
	EventDto updateEvent(@PathVariable("id") Long id, @RequestBody EventDto eventDto) {
		return this.eventService.updateEvent(id, eventDto);

	}

	@DeleteMapping(path = "/{id}")
	Boolean deleteEventById(@PathVariable("id") Long id) {
		return this.eventService.deleteEventById(id);
	}

	@DeleteMapping(path = "/hardDelete/{id}")
	Boolean hardDeleteEventById(@PathVariable("id") Long id) {
		return this.eventService.hardDeleteEventById(id);
	}

}

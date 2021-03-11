package com.jhub.event.ws.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.googlecode.jmapper.JMapper;
import com.jhub.event.io.entity.EventEntity;
import com.jhub.event.repository.EventRepository;
import com.jhub.event.shated.dto.EventDto;
import com.jhub.event.ws.service.EventService;

@Service
public class EventServiceImpl implements EventService {
	private final EventRepository eventRepository;
	private final JMapper<EventEntity, EventDto> eventDtoToEventEntityMapper;
	private final JMapper<EventDto, EventEntity> eventEntityToEventDtoMapper;

	public EventServiceImpl(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
		this.eventDtoToEventEntityMapper = new JMapper<>(EventEntity.class, EventDto.class);
		this.eventEntityToEventDtoMapper = new JMapper<>(EventDto.class, EventEntity.class);
	}

	@Override
	public List<EventDto> getAllEvent() {
		return this.eventRepository.findAll().stream().map(x -> eventEntityToEventDtoMapper.getDestination(x))
				.collect(Collectors.toList());
	}

	@Override
	public EventDto getEventById(Long id) {
		return this.eventRepository.findById(id).map(x -> eventEntityToEventDtoMapper.getDestination(x)).get();

	}

	@Override
	public EventDto createEvent(EventDto eventDto) {
		return eventEntityToEventDtoMapper
				.getDestination(this.eventRepository.save(eventDtoToEventEntityMapper.getDestination(eventDto)));
	}

	@Override
	public EventDto updateEvent(Long id, EventDto eventDto) {
		return this.eventRepository.findById(id).map(x -> {
			x.setName(eventDto.getName());
			x.setUserId(eventDto.getUserId());
			x.setIsActive(true);
			return x;
		}).map(x -> this.eventEntityToEventDtoMapper.getDestination(this.eventRepository.save(x))).get();
	}

	@Override
	public Boolean deleteEventById(Long id) {
		return this.eventRepository.findById(id).map(x -> {
			x.setDeletedAt(LocalDateTime.now());
			x.setIsDeleted(true);
			return x;
		}).map(x -> this.eventRepository.save(x)).get().getIsDeleted() == true ? true : false;
	}

	@Override
	public Boolean hardDeleteEventById(Long id) {
		this.eventRepository.findById(id).ifPresentOrElse(x -> this.eventRepository.delete(x), () -> {
			throw new RuntimeException();
		});
		return true;
	}

}

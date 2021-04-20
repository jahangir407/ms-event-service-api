package com.jhub.event.ws.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jhub.event.io.entity.EventEntity;
import com.jhub.event.repository.EventRepository;
import com.jhub.event.shated.dto.EventDto;
import com.jhub.event.ws.service.EventService;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

	@Mock
	private EventRepository eventRepository;

	private EventService eventService;

	@BeforeEach
	public void setup() {
		this.eventService = new EventServiceImpl(eventRepository);
	}

	@Test
	@DisplayName("Should retrive all  events.")
	void testGetAllEvent() {
//		fail("Not yet implemented");
	}

	@Test
	@DisplayName("Should retrive event by ID.")
	void testGetEventById() {

		EventEntity eventEntity = new EventEntity();

		eventEntity.setId(555L);
		eventEntity.setCreatedAt(LocalDateTime.now());
		eventEntity.setDeletedAt(null);
		eventEntity.setEndTime(LocalTime.NOON);
		eventEntity.setIsActive(true);
		eventEntity.setIsDeleted(false);
		eventEntity.setLastCancellationDate(LocalDate.now());
		eventEntity.setName("Tokyo 2021");
		eventEntity.setNumberOfPeople(50);
		eventEntity.setOpeningDay(LocalDate.now().minusDays(10));
		eventEntity.setStartTime(LocalTime.NOON.minusHours(5));
		eventEntity.setUpdatedAt(LocalDateTime.now());
		eventEntity.setUserId("602dcedcebbb8b5177e6fef2");

		EventDto expectedEventDetails = new EventDto();

		expectedEventDetails.setCreatedAt(LocalDateTime.now());
		expectedEventDetails.setDeletedAt(null);
		expectedEventDetails.setEndTime(LocalTime.NOON);
		expectedEventDetails.setId(555L);
		expectedEventDetails.setIsActive(true);
		expectedEventDetails.setIsDeleted(false);
		expectedEventDetails.setLastCancellationDate(LocalDate.now());
		expectedEventDetails.setName("Tokyo 2021");
		expectedEventDetails.setNumberOfPeople(50);
		expectedEventDetails.setOpeningDay(LocalDate.now().minusDays(10));
		expectedEventDetails.setStartTime(LocalTime.NOON.minusHours(5));
		expectedEventDetails.setUpdatedAt(LocalDateTime.now());
		expectedEventDetails.setUserId("602dcedcebbb8b5177e6fef2");

		/**
		 * write descriptions......
		 */

		Mockito.when(eventRepository.findById(555L)).thenReturn(Optional.of(eventEntity));

		EventDto actualResponse = eventService.getEventById(555L);

		Assertions.assertThat(actualResponse).usingRecursiveComparison().ignoringFields("createdAt", "updatedAt")
				.isEqualTo(expectedEventDetails);

	}

	@Test
	@DisplayName("Should save event.")
	void testCreateEvent() {
//		fail("Not yet implemented");
	}

	@Test
	void testUpdateEvent() {
//		fail("Not yet implemented");
	}

	@Test
	void testDeleteEventById() {
//		fail("Not yet implemented");
	}

	@Test
	void testHardDeleteEventById() {
//		fail("Not yet implemented");
	}

}

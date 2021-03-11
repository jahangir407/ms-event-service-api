package com.jhub.event.ws.ui.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhub.event.shated.dto.EventDto;
import com.jhub.event.ws.service.EventService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = EventController.class)
class EventControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private EventService eventSerivce;

	@Test
	@DisplayName("Should retrive all the event.")
	void getAllEvent() throws Exception {

		EventDto eventDto1 = new EventDto();

		eventDto1.setCreatedAt(LocalDateTime.now());
		eventDto1.setDeletedAt(null);
		eventDto1.setEndTime(LocalTime.now());
		eventDto1.setId(555L);
		eventDto1.setIsActive(true);
		eventDto1.setIsDeleted(false);
		eventDto1.setLastCancellationDate(LocalDate.now());
		eventDto1.setName("Tokyo 2021");
		eventDto1.setNumberOfPeople(50);
		eventDto1.setOpeningDay(LocalDate.now().minusDays(10));
		eventDto1.setStartTime(LocalTime.now().minusHours(5));
		eventDto1.setUpdatedAt(LocalDateTime.now());
		eventDto1.setUserId("602dcedcebbb8b5177e6fef2");

		EventDto eventDto2 = new EventDto();

		eventDto2.setCreatedAt(LocalDateTime.now());
		eventDto2.setDeletedAt(null);
		eventDto2.setEndTime(LocalTime.now());
		eventDto2.setId(666L);
		eventDto2.setIsActive(true);
		eventDto2.setIsDeleted(false);
		eventDto2.setLastCancellationDate(LocalDate.now());
		eventDto2.setName("Osaka 2021");
		eventDto2.setNumberOfPeople(50);
		eventDto2.setOpeningDay(LocalDate.now().minusDays(20));
		eventDto2.setStartTime(LocalTime.now().minusHours(10));
		eventDto2.setUpdatedAt(LocalDateTime.now());
		eventDto2.setUserId("602dcedcebbb8b5177e6fef2");

		EventDto eventDto3 = new EventDto();

		eventDto3.setCreatedAt(LocalDateTime.now());
		eventDto3.setDeletedAt(null);
		eventDto3.setEndTime(LocalTime.now());
		eventDto3.setId(777L);
		eventDto3.setIsActive(true);
		eventDto3.setIsDeleted(false);
		eventDto3.setLastCancellationDate(LocalDate.now());
		eventDto3.setName("Kyoto 2021");
		eventDto3.setNumberOfPeople(50);
		eventDto3.setOpeningDay(LocalDate.now().minusDays(30));
		eventDto3.setStartTime(LocalTime.now().minusHours(20));
		eventDto3.setUpdatedAt(LocalDateTime.now());
		eventDto3.setUserId("602dcedcebbb8b5177e6fef2");

		List<EventDto> eventList = Arrays.asList(eventDto1, eventDto2, eventDto3);

		Mockito.when(eventSerivce.getAllEvent()).thenReturn(eventList);

		String url = "/event";

		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andDo(print()).andReturn();

		String actualResult = mvcResult.getResponse().getContentAsString();

		String expectedResult = objectMapper.writeValueAsString(eventList);

		assertThat(actualResult).isEqualToIgnoringWhitespace(expectedResult);

	}

	@Test
	@DisplayName("Should retrive event by event id.")
	void getEventById() throws Exception {

		Long eventId = 555L;

		EventDto returnedEvent = new EventDto();

		returnedEvent.setCreatedAt(LocalDateTime.now());
		returnedEvent.setDeletedAt(null);
		returnedEvent.setEndTime(LocalTime.now());
		returnedEvent.setId(555L);
		returnedEvent.setIsActive(true);
		returnedEvent.setIsDeleted(false);
		returnedEvent.setLastCancellationDate(LocalDate.now());
		returnedEvent.setName("Tokyo 2021");
		returnedEvent.setNumberOfPeople(50);
		returnedEvent.setOpeningDay(LocalDate.now().minusDays(10));
		returnedEvent.setStartTime(LocalTime.now().minusHours(5));
		returnedEvent.setUpdatedAt(LocalDateTime.now());
		returnedEvent.setUserId("602dcedcebbb8b5177e6fef2");

		when(eventSerivce.getEventById(eventId)).thenReturn(returnedEvent);

		String url = "/event/555";

		MvcResult result = mockMvc.perform(get(url)).andExpect(status().isOk()).andDo(print()).andReturn();

		EventDto actualResponse = objectMapper.readValue(result.getResponse().getContentAsString(), EventDto.class);

		assertThat(actualResponse.getId()).isEqualTo(555L);
		assertThat(actualResponse.getName()).isEqualTo("Tokyo 2021");

	}

	@Test
	@DisplayName("Should create a new event.")
	void createEvent() throws JsonProcessingException, Exception {

		EventDto eventRequestBody = new EventDto();

		eventRequestBody.setEndTime(LocalTime.now());
		eventRequestBody.setLastCancellationDate(LocalDate.now());
		eventRequestBody.setName("Tokyo 2021");
		eventRequestBody.setNumberOfPeople(50);
		eventRequestBody.setOpeningDay(LocalDate.now().minusDays(10));
		eventRequestBody.setStartTime(LocalTime.now().minusHours(5));
		eventRequestBody.setUserId("602dcedcebbb8b5177e6fef2");

		EventDto savedEvent = new EventDto();

		savedEvent.setCreatedAt(LocalDateTime.now());
		savedEvent.setDeletedAt(null);
		savedEvent.setEndTime(LocalTime.now());
		savedEvent.setId(555L);
		savedEvent.setIsActive(true);
		savedEvent.setIsDeleted(false);
		savedEvent.setLastCancellationDate(LocalDate.now());
		savedEvent.setName("Tokyo 2021");
		savedEvent.setNumberOfPeople(50);
		savedEvent.setOpeningDay(LocalDate.now().minusDays(10));
		savedEvent.setStartTime(LocalTime.now().minusHours(5));
		savedEvent.setUpdatedAt(LocalDateTime.now());
		savedEvent.setUserId("602dcedcebbb8b5177e6fef2");

		when(eventSerivce.createEvent(Mockito.any(EventDto.class))).thenReturn(savedEvent);

		String url = "/event";

		MvcResult mvcResult = mockMvc
				.perform(post(url).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(eventRequestBody)))
				.andExpect(status().isOk()).andDo(print()).andReturn();

		EventDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), EventDto.class);

		assertThat(actual.getId()).isEqualTo(555L);
		assertThat(actual.getName()).isEqualTo(eventRequestBody.getName());

	}

	@Test
	@DisplayName("Event name should not be empty")
	void eventNameShouldNotBeEmpty() throws JsonProcessingException, Exception {

		EventDto eventRequestBody = new EventDto();

		eventRequestBody.setEndTime(LocalTime.now());
		eventRequestBody.setLastCancellationDate(LocalDate.now());
		// providing empty string into the name field.
		eventRequestBody.setName("");
		eventRequestBody.setNumberOfPeople(50);
		eventRequestBody.setOpeningDay(LocalDate.now().minusDays(10));
		eventRequestBody.setStartTime(LocalTime.now().minusHours(5));
		eventRequestBody.setUserId("602dcedcebbb8b5177e6fef2");

		String url = "/event";

		mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(eventRequestBody))).andExpect(status().isBadRequest())
				.andDo(print());

		Mockito.verify(eventSerivce, times(0)).createEvent(eventRequestBody);

	}

	@Test
	@DisplayName("Should update an existing event information.")
	void updateExistingEvent() throws JsonProcessingException, Exception {

		Long eventId = 555L;
		EventDto editedEvent = new EventDto();

		editedEvent.setCreatedAt(LocalDateTime.now());
		editedEvent.setDeletedAt(null);
		editedEvent.setEndTime(LocalTime.now());
		editedEvent.setId(eventId);
		editedEvent.setIsActive(true);
		editedEvent.setIsDeleted(false);
		editedEvent.setLastCancellationDate(LocalDate.now());
		editedEvent.setName("Osaka 2021");
		editedEvent.setNumberOfPeople(50);
		editedEvent.setOpeningDay(LocalDate.now().minusDays(10));
		editedEvent.setStartTime(LocalTime.now().minusHours(5));
		editedEvent.setUpdatedAt(LocalDateTime.now());
		editedEvent.setUserId("602dcedcebbb8b5177e6fef2");

		EventDto updatedEvent = new EventDto();

		updatedEvent.setCreatedAt(LocalDateTime.now());
		updatedEvent.setDeletedAt(null);
		updatedEvent.setEndTime(LocalTime.now());
		updatedEvent.setId(eventId);
		updatedEvent.setIsActive(true);
		updatedEvent.setIsDeleted(false);
		updatedEvent.setLastCancellationDate(LocalDate.now());
		updatedEvent.setName("Osaka 2021");
		updatedEvent.setNumberOfPeople(50);
		updatedEvent.setOpeningDay(LocalDate.now().minusDays(10));
		updatedEvent.setStartTime(LocalTime.now().minusHours(5));
		updatedEvent.setUpdatedAt(LocalDateTime.now());
		updatedEvent.setUserId("602dcedcebbb8b5177e6fef2");

		when(eventSerivce.updateEvent(Mockito.anyLong(), Mockito.any(EventDto.class))).thenReturn(updatedEvent);

		String url = "/event/{id}";

		MvcResult result = mockMvc
				.perform(put(url, eventId).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(editedEvent)))
				.andExpect(status().isOk()).andDo(print()).andReturn();

		EventDto actualResponse = objectMapper.readValue(result.getResponse().getContentAsString(), EventDto.class);

		assertThat(actualResponse.getId()).isEqualTo(555L);
		assertThat(actualResponse.getName()).isEqualTo("Osaka 2021");
	}

	@Test
	@DisplayName("Should soft delete event by event ID.")
	void deleteEventById() throws Exception {
		Long eventId = 555L;

//		when(eventSerivce.deleteEventById(555L)).thenReturn(Boolean.TRUE);
		given(eventSerivce.deleteEventById(eventId)).willReturn(Boolean.TRUE);

		String url = "/event/{id}";
		MvcResult result = mockMvc
				.perform(
						delete(url, eventId).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andReturn();

		Boolean actual = objectMapper.readValue(result.getResponse().getContentAsString(), Boolean.class);

		assertTrue(actual);
	}

	@Test
	@DisplayName("Should permanently delete event from database.")
	void hardDeleteEventById() throws Exception {

		Long eventId = 666L;

		when(eventSerivce.hardDeleteEventById(Mockito.anyLong())).thenReturn(Boolean.TRUE);

		String url = "/event/hardDelete/{id}";

		MvcResult result = mockMvc.perform(delete(url, eventId)).andExpect(status().isOk()).andDo(print()).andReturn();

		Boolean actual = objectMapper.readValue(result.getResponse().getContentAsString(), Boolean.class);
		assertTrue(actual);
	}

}

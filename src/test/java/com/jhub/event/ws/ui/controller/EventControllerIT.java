package com.jhub.event.ws.ui.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhub.event.shated.dto.EventDto;
import com.jhub.event.ws.service.EventService;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EventControllerIT {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	EventService eventService;

	@Test
	@DisplayName("IT Case 3: Should retrive all the event from database.")
	@Order(3)
	void getAllEvent() throws Exception {
		String url = "/event";
		MvcResult result = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		//MvcResult result = mockMvc.perform(get(url)).andExpect(status().isOk()).andDo(print()).andReturn(); andDo(print())-> print to the standard console

		List<EventDto> actualResponse = Arrays
				.asList(objectMapper.readValue(result.getResponse().getContentAsString(), EventDto[].class));

		assertThat(actualResponse).hasSize(1);

	}

	@Test
	@DisplayName("IT Case 2: Should retrive event by event ID.")
	@Order(2)
	void getEventById() throws Exception {

		Long eventId = 1L;

		String url = "/event/{id}";

		MvcResult result = mockMvc.perform(get(url, eventId).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		EventDto actual = objectMapper.readValue(result.getResponse().getContentAsString(), EventDto.class);
		EventDto expected = eventService.getEventById(actual.getId());

		assertThat(actual, is(expected));
	}

	@Test
	@DisplayName("IT Case 1: Should create a new event.")
	@Order(1)
	void createEvent() throws JsonProcessingException, Exception {
		EventDto eventRequestBody = new EventDto();

		eventRequestBody.setEndTime(LocalTime.now());
		eventRequestBody.setLastCancellationDate(LocalDate.now());
		eventRequestBody.setName("Tokyo 2021");
		eventRequestBody.setNumberOfPeople(50);
		eventRequestBody.setOpeningDay(LocalDate.now().minusDays(10));
		eventRequestBody.setStartTime(LocalTime.now().minusHours(5));
		eventRequestBody.setUserId("602dcedcebbb8b5177e6fef2");

		String url = "/event";

		MvcResult mvcResult = mockMvc
				.perform(post(url).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(eventRequestBody)))
				.andExpect(status().isOk()).andReturn();

		EventDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), EventDto.class);

		EventDto expect = eventService.getEventById(actual.getId());
		assertThat(actual, is(expect));
	}

	@Test
	@DisplayName("IT Case 4: Should update existing event details.")
	@Order(4)
	void updateEventById() throws JsonProcessingException, Exception {

		Long eventId = 1L;
		EventDto eventRequestBody = new EventDto();

		eventRequestBody.setEndTime(LocalTime.now());
		eventRequestBody.setLastCancellationDate(LocalDate.now());
		eventRequestBody.setName("Osaka 2021");
		eventRequestBody.setNumberOfPeople(100);
		eventRequestBody.setOpeningDay(LocalDate.now().minusDays(10));
		eventRequestBody.setStartTime(LocalTime.now().minusHours(5));
		eventRequestBody.setUserId("602dcedcebbb8b5177e6fef2");

		String url = "/event/{id}";

		MvcResult result = mockMvc
				.perform(put(url, eventId).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(eventRequestBody)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		EventDto actual = objectMapper.readValue(result.getResponse().getContentAsString(), EventDto.class);

		EventDto expected = eventService.getEventById(actual.getId());
		assertThat(actual, is(expected));

	}

	@Test
	@DisplayName("IT Case 5: Should soft delete an event.")
	@Order(5)
	void deleteEventById() throws Exception {
		Long eventId = 1l;

		String url = "/event/{id}";

		MvcResult result = mockMvc.perform(delete(url, eventId)).andExpect(status().isOk()).andReturn();

		Boolean actual = objectMapper.readValue(result.getResponse().getContentAsString(), Boolean.class);

		assertTrue(actual);
	}

	@Test
	@DisplayName("IT Case 6: Should permanetly delete an event from database.")
	@Order(6)

	void hardDeleteEventById() throws Exception {

		Long eventId = 1l;

		String url = "/event/hardDelete/{id}";

		MvcResult result = mockMvc.perform(delete(url, eventId)).andExpect(status().isOk()).andReturn();

		Boolean actual = objectMapper.readValue(result.getResponse().getContentAsString(), Boolean.class);

		assertTrue(actual);

	}

}

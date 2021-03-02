package com.jhub.event.shated.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.googlecode.jmapper.annotations.JGlobalMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JGlobalMap
public class EventDto {

	private Long id;
	private String userId;

	private String name;
	private LocalDate openingDay;
	private LocalTime startTime;
	private LocalTime endTime;
	private int numberOfPeople;
	private LocalDate lastReservationDate;
	private LocalDate lastCancellationDate;
	private Boolean isActive;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;
	private Boolean isDeleted;

}

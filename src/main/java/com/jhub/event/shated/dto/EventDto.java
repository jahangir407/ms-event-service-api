package com.jhub.event.shated.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;

import com.googlecode.jmapper.annotations.JGlobalMap;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@JGlobalMap
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class EventDto {

	@EqualsAndHashCode.Include
	private Long id;
	@EqualsAndHashCode.Include
	private String userId;

	@NotBlank
	@EqualsAndHashCode.Include
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

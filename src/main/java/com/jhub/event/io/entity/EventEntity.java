package com.jhub.event.io.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;

import com.googlecode.jmapper.annotations.JGlobalMap;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JGlobalMap
public class EventEntity extends BaseEntity {

	private String userId;

	private String name;
	private LocalDate openingDay;
	private LocalTime startTime;
	private LocalTime endTime;
	private int numberOfPeople;
	private LocalDate lastReservationDate;
	private LocalDate lastCancellationDate;
	private Boolean isActive;

}

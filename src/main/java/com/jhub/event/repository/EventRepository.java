package com.jhub.event.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jhub.event.io.entity.EventEntity;

@Repository
public interface EventRepository extends PagingAndSortingRepository<EventEntity, Long> {
	List<EventEntity> findAll();

}

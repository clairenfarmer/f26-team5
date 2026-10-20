package com.csc340.hobbycircles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csc340.hobbycircles.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByHobbygroupIdOrderById(Long hobbygroupId);

    List<Event> findAllByHobbygroupIdAndStatusIgnoreCaseOrderById(Long hobbygroupId, String status);

    List<Event> findAllByCategoryIgnoreCaseAndStatusIgnoreCaseOrderById(String category, String status);

    List<Event> findAllByStatusIgnoreCaseOrderById(String string);
}

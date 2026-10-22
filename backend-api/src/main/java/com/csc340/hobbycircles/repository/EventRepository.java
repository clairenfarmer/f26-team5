package com.csc340.hobbycircles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csc340.hobbycircles.entity.Event;
import com.csc340.hobbycircles.entity.Interest;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByHobbygroupIdOrderById(Long hobbygroupId);

    List<Event> findAllByInterestOrderById(Interest interest);

    List<Event> findAllByHobbygroupIdAndStatusIgnoreCaseOrderById(Long hobbygroupId, String status);

    List<Event> findAllByInterestAndStatusIgnoreCaseOrderById(Interest interest, String status);
    
    List<Event> findAllByStatusIgnoreCaseOrderById(String string);
}

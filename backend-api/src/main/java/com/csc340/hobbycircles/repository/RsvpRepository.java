package com.csc340.hobbycircles.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.csc340.hobbycircles.entity.RSVP;

public interface RsvpRepository extends JpaRepository<com.csc340.hobbycircles.entity.RSVP, Long> {
    boolean existsByIndividualIdAndEventId(Long individualId, Long eventId);

    List<RSVP> findAllByEventId(Long eventId);
}

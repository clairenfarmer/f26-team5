package com.csc340.hobbycircles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.csc340.hobbycircles.entity.HobbyGroup;


public interface HobbyGroupRepository extends JpaRepository<HobbyGroup, Long> {
    boolean existsByEmailIgnoreCase(String email);

    List<HobbyGroup> findByInterestContainingIgnoreCase(String category);

    @Query ("""
        select distinct group
        from HobbyGroup group
        left join group.events event
        where lower(coalesce(group.interests, '')) like lower(concat('%', :category, '%'))
            or lower(event.category) = lower(:category)
                and upper(event.status) = 'PUBLISHED'
    """)
    List<HobbyGroup> findMatchingCategory(@Param("category") String category);
    List<HobbyGroup> findByHobbyGroupId(Long hobbyGroupId);
}

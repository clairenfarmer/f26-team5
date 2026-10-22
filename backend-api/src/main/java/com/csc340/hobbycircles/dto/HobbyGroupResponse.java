package com.csc340.hobbycircles.dto;
import com.csc340.hobbycircles.entity.HobbyGroup;
import com.csc340.hobbycircles.entity.Interest;

public record HobbyGroupResponse(
    Long id,
    String name,
    String email,
    Interest interest
) {
    public static HobbyGroupResponse from(HobbyGroup hobbyGroup) {
        return new HobbyGroupResponse(
            hobbyGroup.getId(),
            hobbyGroup.getName(),
            hobbyGroup.getEmail(),
            hobbyGroup.getInterest()
        );
    }
}
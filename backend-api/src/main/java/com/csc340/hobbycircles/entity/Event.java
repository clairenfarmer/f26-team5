package com.csc340.hobbycircles.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity 
@Table(name = "events")
public class Event {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String name;

    @Size(max = 2000)
    @Column(length = 2000)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Interest interest;

    @Size(max = 255)
    @Column(length = 255)
    private String location;

    @Size(max = 50)
    @Column(length = 50)
    private LocalDateTime time;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hobbygroup_id", nullable = false)
    private HobbyGroup hobbygroup;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rsvp_id", nullable = false)
    private RSVP rsvp;

    // constructors, getters, and setters
    public Event() {
    }

    public Event(String name, String description, Interest interest, String location, LocalDateTime time, HobbyGroup hobbygroup) {
        this.name = name;
        this.description = description;
        this.interest = interest;
        this.location = location;
        this.time = time;
        this.status = "PUBLISHED"; // default status
        this.hobbygroup = hobbygroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HobbyGroup getHobbygroup() {
        return hobbygroup;
    }

    public void setHobbygroup(HobbyGroup hobbygroup) {
        this.hobbygroup = hobbygroup;
    }

    public RSVP getRsvp() {
        return rsvp;
    }
}

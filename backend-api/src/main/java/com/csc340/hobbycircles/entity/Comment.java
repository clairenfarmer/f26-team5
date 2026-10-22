package com.csc340.hobbycircles.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

@Entity @Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 2000)
    @Column(length = 2000)
    private String message;

    @Size(max = 2000)
    @Column(length = 2000)
    private String replyMessage;

     @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "individual_id", nullable = false)
    private Individual individual;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hobbygroup_id", nullable = false)
    private HobbyGroup hobbyGroup;

    // constructors, getters, and setters
    public Comment() {
    }

    public Comment(String message, String replyMessage, Individual individual, HobbyGroup hobbyGroup) {
        this.message = message;
        this.replyMessage = replyMessage;
        this.individual = individual;
        this.hobbyGroup = hobbyGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public HobbyGroup getHobbyGroup() {
        return hobbyGroup;
    }

    public void setHobbyGroup(HobbyGroup hobbyGroup) {
        this.hobbyGroup = hobbyGroup;
    }

    
}

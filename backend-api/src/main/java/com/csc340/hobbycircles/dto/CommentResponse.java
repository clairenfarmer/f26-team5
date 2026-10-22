package com.csc340.hobbycircles.dto;

import com.csc340.hobbycircles.entity.Comment;

public record CommentResponse(
        Long id,
        String message,
        String replyMessage,
        Long individualId,
        String individualName,
        Long hobbyGroupId,
        String hobbyGroupName) {

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(comment.getId(), comment.getMessage(),
                comment.getReplyMessage(), comment.getIndividual().getId(),
                comment.getIndividual().getName(), comment.getHobbyGroup().getId(),
                comment.getHobbyGroup().getName());
    }
}

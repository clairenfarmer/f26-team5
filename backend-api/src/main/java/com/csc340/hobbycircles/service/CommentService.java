package com.csc340.hobbycircles.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.csc340.hobbycircles.repository.CommentRepository;
import com.csc340.hobbycircles.repository.IndividualRepository;
import com.csc340.hobbycircles.repository.HobbyGroupRepository;
import com.csc340.hobbycircles.dto.CommentReplyRequest;
import com.csc340.hobbycircles.dto.CommentRequest;
import com.csc340.hobbycircles.dto.CommentResponse;
import com.csc340.hobbycircles.entity.Comment;
import com.csc340.hobbycircles.entity.HobbyGroup;
import com.csc340.hobbycircles.entity.Individual;
import java.util.List;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final IndividualRepository individualRepository;
    private final HobbyGroupRepository hobbyGroupRepository;

    public CommentService(
            CommentRepository commentRepository,
            IndividualRepository individualRepository,  
            HobbyGroupRepository hobbyGroupRepository) {
        this.commentRepository = commentRepository;
        this.individualRepository = individualRepository;
        this.hobbyGroupRepository = hobbyGroupRepository;
    }

    public CommentResponse create(Long individualId, Long hobbyGroupId, CommentRequest request) {
        HobbyGroup hobbyGroup = hobbyGroupRepository.findById(hobbyGroupId)
                .orElseThrow(() -> new NotFoundException(
                        "Hobby Group " + hobbyGroupId + " was not found."));
        
        Individual individual = individualRepository.findById(individualId)
                .orElseThrow(() -> new NotFoundException(
                        "Individual " + individualId + " was not found."));

        Comment comment = new Comment();
        comment.setHobbyGroup(hobbyGroup);
        comment.setIndividual(individual);
        comment.setMessage(request.message());
        hobbyGroup.addComment(comment); 
        return CommentResponse.from(commentRepository.save(comment));
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> listForIndividual(Long individualId) {
        if (!individualRepository.existsById(individualId)) {
            throw new NotFoundException("Individual " + individualId + " was not found.");
        }
        return commentRepository.findAllByIndividualId(individualId)
                .stream().map(CommentResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> listForHobbyGroup(Long hobbyGroupId) {
        if (!hobbyGroupRepository.existsById(hobbyGroupId)) {
            throw new NotFoundException("Hobby Group " + hobbyGroupId + " was not found.");
        }
        return commentRepository.findAllByHobbygroupId(hobbyGroupId)
                .stream().map(CommentResponse::from).toList();
    }

    public CommentResponse reply(Long hobbyGroupId, Long commentId, CommentReplyRequest request) {
        if (!hobbyGroupRepository.existsById(hobbyGroupId)) {
            throw new NotFoundException("Hobby Group " + hobbyGroupId + " was not found.");
        }
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment " + commentId + " was not found."));
        if (!comment.getHobbyGroup().getId().equals(hobbyGroupId)) {
            throw new NotFoundException("Comment " + commentId + " was not found for Hobby Group "
                    + hobbyGroupId + ".");
        }
        comment.setReplyMessage(request.replyMessage().trim());
        return CommentResponse.from(commentRepository.save(comment));
    }

    
}

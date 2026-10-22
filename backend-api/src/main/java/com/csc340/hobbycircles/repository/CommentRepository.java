package com.csc340.hobbycircles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.csc340.hobbycircles.entity.Comment;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>  {
    List<Comment> findAllByIndividualId(Long individualId);
    List<Comment> findAllByHobbyGroupId(Long hobbyGroupId);
}


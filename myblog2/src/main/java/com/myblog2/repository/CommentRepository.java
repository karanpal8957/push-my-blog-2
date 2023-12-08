package com.myblog2.repository;

import com.myblog2.entity.Comment;
import com.myblog2.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(Long postId);

    ;
}

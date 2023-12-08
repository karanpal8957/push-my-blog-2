package com.myblog2.service;

import com.myblog2.entity.Comment;
import com.myblog2.entity.Post;
import com.myblog2.exception.ResourceNotFound;
import com.myblog2.payload.CommentDto;
import com.myblog2.repository.CommentRepository;
import com.myblog2.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private ModelMapper modelMapper;


    public CommentDto createComment(CommentDto commentDto,Long postId) {
     Post post=   postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFound("post not found By Id:"+postId)
        );
           Comment comment= mapToEntity(commentDto);
           comment.setPost(post);
        Comment saveComment=   commentRepo.save(comment);
            return mapToDto(saveComment);

    }
    public Comment mapToEntity(CommentDto commentDto){
        return modelMapper.map(commentDto,Comment.class);
    }
    public CommentDto mapToDto(Comment saveComment){
        return modelMapper.map(saveComment,CommentDto.class);
    }

    public List<CommentDto> getPostWithComment(Long postId) {
      List<Comment> lists= commentRepo.findByPostId(postId);
      return lists.stream().map(saveComment->mapToDto(saveComment)).collect(Collectors.toList());
    }
}

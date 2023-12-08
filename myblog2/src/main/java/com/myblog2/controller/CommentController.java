package com.myblog2.controller;

import com.myblog2.payload.CommentDto;
import com.myblog2.payload.PostDto;
import com.myblog2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //http://localhost:8080/api/comments/{postId}
    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Long postId){
         CommentDto dto=   commentService.createComment(commentDto,postId);
         return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/comments/{postId}
    @GetMapping("/{postId}")
    public List<CommentDto> getPostWithComments(@PathVariable Long postId){
        List<CommentDto> dtos=   commentService.getPostWithComment(postId);
        return dtos;
    }
}

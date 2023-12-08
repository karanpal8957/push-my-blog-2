package com.myblog2.controller;

import com.myblog2.entity.Post;
import com.myblog2.payload.CommentDto;
import com.myblog2.payload.PostDto;
import com.myblog2.payload.PostResponse;
import com.myblog2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;


    @PostMapping("/n")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createPost(
            @Valid @RequestBody PostDto postDto, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        PostDto dto = postService.savePost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    //http://localhost:8080/api/posts/1
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
        PostDto dto = postService.getPost(id);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    //http://localhost:8080/api/posts
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int
                    pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int
                    pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String
                    sortDir
    ){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    //http://localhost:8080/api/posts/1
   @PutMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Long id) {
        PostDto dto = postService.updatePost(postDto, id);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }



}
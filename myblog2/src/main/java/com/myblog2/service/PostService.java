package com.myblog2.service;

import com.myblog2.entity.Post;
import com.myblog2.payload.PostDto;
import com.myblog2.payload.PostResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
   PostDto savePost(PostDto postDto);

   PostDto getPost(Long id);



   PostDto updatePost(PostDto postDto, Long id);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);


}

package com.myblog2.serviceImpl;

import com.myblog2.entity.Comment;
import com.myblog2.entity.Post;
import com.myblog2.exception.ResourceNotFound;
import com.myblog2.payload.PostDto;
import com.myblog2.payload.PostResponse;
import com.myblog2.repository.CommentRepository;
import com.myblog2.repository.PostRepository;
import com.myblog2.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepository postRepo;

    @Autowired
    private CommentRepository commentRepo;


    @Override
    public PostDto savePost(PostDto postDto) {
      Post post=   mapToEntity(postDto);
      Post savePost=  postRepo.save(post);
         return mapToDto(savePost);

    }

    @Override
    public PostDto getPost(Long id) {
       Post savePost= postRepo.findById(id).orElseThrow(
               ()->new ResourceNotFound("post not found by id:"+id)

       );
        return mapToDto(savePost);
    }



    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
       Post post=   postRepo.findById(id).orElseThrow(
                  ()-> new ResourceNotFound("post not found By id:"+id)
          );
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
       Post savePost= postRepo.save(post);
       return mapToDto(savePost);
    }



    public Post mapToEntity(PostDto postDto){
     Post post=   modelMapper.map(postDto,Post.class);
     return post;
    }
     public PostDto mapToDto(Post savePost){

        return modelMapper.map(savePost,PostDto.class);
     }
    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String
            sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepo.findAll(pageable);
        // get content for page object
        List<Post> listOfPosts = posts.getContent();
        List<PostDto> content= listOfPosts.stream().map(savePost ->
                mapToDto(savePost)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;
    }


}

package com.crud.crud.operation.service;

import com.crud.crud.operation.dto.PostDto;
import com.crud.crud.operation.dto.PostResponse;
import com.crud.crud.operation.entity.Post;
import com.crud.crud.operation.exception.ResourceNotFoundException;
import com.crud.crud.operation.repo.PostRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepo repo;

    @Autowired
    private ModelMapper mapper;

    public PostDto savePost(PostDto postDto){
        Post post = mapToEntity(postDto);
        Post post1 = repo.save(post);
        PostDto postDto1 = mapToDto(post1);
        return postDto1;
    }

    public PostDto updatePost(int id,PostDto postDto) {
       // int id = postDto.getId();
        Optional<Post> optionalPost = repo.findById(id);

        if(optionalPost.isPresent()){
            Post post2 = optionalPost.get();
            post2.setDescription(postDto.getDescription());
            //Post post = mapToEntity(postDto);
          //  Post post1 = optionalPost.get();
            Post save = repo.save(post2);
            PostDto dto = mapToDto(save);
            return dto;
        }

     else{
         throw new RuntimeException("Id not found :" + id );
        }

    }

    public PostResponse allPost(int pageNo, int pageSize, String sortBy, String sortDir){
       Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
               Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
      Pageable pageable= PageRequest.of(pageNo, pageSize, sort);
        Page<Post> postPage = repo.findAll(pageable);
        List<Post> content = postPage.getContent();
        List<PostDto> dtoList = content.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        PostResponse postResponse= new PostResponse();
        postResponse.setPostDtos(dtoList);
        postResponse.setPageNo(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalPage(postPage.getTotalPages());
        postResponse.setTotalElement(postPage.getTotalElements());
        postResponse.setLast(postPage.isLast());
        return postResponse;
    }

   /* public PostDto updatePostById(int id, PostDto dto) {
        Optional<Post> optionalPost = repo.findById(id);
        if(optionalPost.isPresent()) {

            Post post2 = optionalPost.get();

            post2.setDescription(dto.getDescription());

            Post post1 = repo.save(post2);
            PostDto postDto = mapToDto(post1);
            return postDto;

        }else{
            return null;
        }


        }*/

    public PostDto updatePostById(int id,PostDto postDto) {
        // int id = postDto.getId();
        Optional<Post> optionalPost = repo.findById(id);

        if(optionalPost.isPresent()){
            Post post2 = optionalPost.get();
            post2.setTitle(postDto.getTitle());
            post2.setDescription(postDto.getDescription());
            Post save = repo.save(post2);
            PostDto dto = mapToDto(save);
            return dto;
        }

        else{
            throw new RuntimeException("Id not found :" + id );
        }

    }

    Post mapToEntity(PostDto postDto){
        return mapper.map(postDto, Post.class);
    }

    PostDto mapToDto(Post post){
        return mapper.map(post, PostDto.class);
    }


}

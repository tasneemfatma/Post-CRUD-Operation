package com.crud.crud.operation.controller;

import com.crud.crud.operation.dto.PostDto;
import com.crud.crud.operation.dto.PostResponse;
import com.crud.crud.operation.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService service;
    @PostMapping("/")
    public ResponseEntity<PostDto> savePost(@RequestBody PostDto dto){
        PostDto postDto = service.savePost(dto);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<PostResponse> allPost(@RequestParam(value="pageNo", defaultValue = "0", required = false) int pageNo,
                                                 @RequestParam(value="pageSize", defaultValue = "5", required = false) int pageSize,
                                                  @RequestParam(value="sortBy", defaultValue = "title", required = false) String sortBy,
                                                  @RequestParam(value="sortDir", defaultValue = "asc", required = false)  String sortDir){
        PostResponse postResponse = service.allPost(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);


    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable int id,@RequestBody PostDto postDto){
        PostDto postDto1 = service.updatePost(id,postDto);
        return new ResponseEntity<PostDto>(postDto1, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchPost(@PathVariable int id, @RequestBody PostDto dto){
        PostDto postDto = service.updatePostById(id, dto);
        if(postDto!=null){
            return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("Post not found with id :" + id, HttpStatus.NOT_FOUND);

        }


    }
    @DeleteMapping
    public void deletePost(){
        
    }

        @GetMapping
    public void getPost(){
        
    }




}

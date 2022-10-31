package com.ad.blogpost.controllers;

import com.ad.blogpost.payloads.PostDto;
import com.ad.blogpost.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    //  CREATE
    @PostMapping("user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPosts(@RequestBody PostDto postDto, @PathVariable Long categoryId, @PathVariable Long userId) {
        PostDto postDto1 = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId){
        PostDto postDto = this.postService.getPost(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPost(){
        List<PostDto> postDtoList = this.postService.getAllPosts();
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategoryId(@PathVariable Long categoryId){
        List<PostDto> postDtoList = this.postService.getAllPostsByCategoryId(categoryId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Long userId){
        List<PostDto> postDtoList = this.postService.getAllPostsByUserId(userId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }
}

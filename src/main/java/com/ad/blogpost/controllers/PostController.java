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

    // GET POST BY POST ID
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId){
        PostDto postDto = this.postService.getPost(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    // GET ALL POSTS
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPost(){
        List<PostDto> postDtoList = this.postService.getAllPosts();
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    // GET all POSTS BY CATEGORY ID
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategoryId(@PathVariable Long categoryId){
        List<PostDto> postDtoList = this.postService.getAllPostsByCategoryId(categoryId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    // GET all POSTS created BY a USER ID
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Long userId){
        List<PostDto> postDtoList = this.postService.getAllPostsByUserId(userId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    // GET all POSTS created BY a USERNAME
    @GetMapping("/posts/name/{name}")
    public ResponseEntity<List<PostDto>> getPostsByUserName(@PathVariable String name){
        List<PostDto> postDtoList = this.postService.getAllPostsByUserName(name);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    //  CREATE POST
    @PostMapping("user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Long categoryId, @PathVariable Long userId) {
        PostDto postDto1 = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }

    // UPDATE POST BY ID
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable String postId, @RequestBody PostDto postDto){
        PostDto updatedPost = this.postService.updatePost(postDto, Long.parseLong(postId));
        return new ResponseEntity<>(updatedPost,HttpStatus.ACCEPTED);
    }

    // DELETE POST BY ID
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable String postId){
        try{
            this.postService.deletePost(Long.parseLong(postId));
            return new ResponseEntity<>("Post Deleted", HttpStatus.ACCEPTED);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Resource Not Found", HttpStatus.BAD_REQUEST);
        }
    }
}

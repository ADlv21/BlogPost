package com.ad.blogpost.controllers;

import com.ad.blogpost.config.AppConstants;
import com.ad.blogpost.payloads.PostDto;
import com.ad.blogpost.payloads.PostResponse;
import com.ad.blogpost.services.FileService;
import com.ad.blogpost.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    // GET POST BY POST ID
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId){
        PostDto postDto = this.postService.getPost(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    // GET ALL POSTS
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDir
    ){
        PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
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

    // Searches USING TITLE
    @GetMapping("posts/search/{keyword}")
    public ResponseEntity<PostResponse> searchPostsByTitle(
            @PathVariable String keyword,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize
    ){
        PostResponse postResponse = this.postService.searchPostsByTitle(keyword,pageNumber,pageSize);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    // Searches USING Content
    @GetMapping("posts/search/content/{keyword}")
    public ResponseEntity<PostResponse> searchPostsByContent(
            @PathVariable String keyword,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize
    ){
        PostResponse postResponse = this.postService.searchPostsByContent(keyword,pageNumber,pageSize);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
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

    // POST IMAGE UPLOAD
    @PostMapping("/posts/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @PathVariable String postId,
            @RequestParam("image") MultipartFile image
            ) throws IOException {

        PostDto postDto = this.postService.getPost(Long.parseLong(postId));

        String fileName = this.fileService.uploadImage(path,image);
        System.out.println("Controller : "+ fileName);
        postDto.setImageName(fileName);
        PostDto updatedPost = this.postService.updatePost(postDto, Long.parseLong(postId));

        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping(value = "/posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response) throws IOException {
        InputStream resource = this.fileService.getImage(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}

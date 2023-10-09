package com.ad.blogpost.services;

import com.ad.blogpost.payloads.PostDto;
import com.ad.blogpost.payloads.PostResponse;

import java.util.List;

public interface PostService {

    // CREATE POST
    PostDto createPost(PostDto postDto, Long userId, Long categoryId);

    // UPDATE POST
    PostDto updatePost(PostDto postDto, Long postId);

    // DELETE POST
    void deletePost(Long postId);

    // GET POST
    PostDto getPost(Long postId);

    // GET POSTS
    PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir);

    // GET ALL POST BY CATEGORY ID
    List<PostDto> getAllPostsByCategoryId(Long categoryId);

    // GET ALL POST BY CATEGORY NAME
    List<PostDto> getAllPostsByCategoryName(String categoryName);

    // GET ALL POSTS BY USER ID
    List<PostDto> getAllPostsByUserId(Long userId);

    // GET ALL POSTS BY USERNAME
    List<PostDto> getAllPostsByUserName(String userName);

    PostResponse searchPostsByTitle(String keyword, int pageNumber, int pageSize);

    PostResponse searchPostsByContent(String keyword, int pageNumber, int pageSize);

}

package com.ad.blogpost.services;

import com.ad.blogpost.entities.User;
import com.ad.blogpost.payloads.PostDto;

import java.util.List;

@SuppressWarnings("ALL")
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
    List<PostDto> getAllPosts();

    // GET ALL POST BY CATEGORY ID
    List<PostDto> getAllPostsByCategoryId(Long categoryId);

    // GET ALL POST BY CATEGORY NAME
    List<PostDto> getAllPostsByCategoryName(String categoryName);

    // GET ALL POSTS BY USER ID
    List<User> getAllPostsByUserId(Long userId);

    // GET ALL POSTS BY USERNAME
    List<User> getAllPostsByUserName(String userName);

}

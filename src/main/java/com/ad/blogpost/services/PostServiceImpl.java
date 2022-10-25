package com.ad.blogpost.services;

import com.ad.blogpost.entities.Category;
import com.ad.blogpost.entities.Post;
import com.ad.blogpost.entities.User;
import com.ad.blogpost.exceptions.ResourceNotFoundException;
import com.ad.blogpost.payloads.PostDto;
import com.ad.blogpost.repositories.CategoryRepo;
import com.ad.blogpost.repositories.PostRepo;
import com.ad.blogpost.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo catRepo;

    @Autowired
    private ModelMapper modelMapper;

    private PostDto postToDto(Post post) {
        return this.modelMapper.map(post, PostDto.class);
    }

    private Post dtoToPost(PostDto postDto) {
        return this.modelMapper.map(postDto, Post.class);
    }

    @Override
    public PostDto createPost(PostDto postDto, Long userId, Long categoryId) {


        User user = this.userRepo
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User ID", userId));

        Category category = this.catRepo
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryId));

        Post post = dtoToPost(postDto);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        return postToDto(this.postRepo.save(post));
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {


        return null;
    }

    @Override
    public void deletePost(Long postId) {
        //this.postRepo.deleteById(postId);
    }

    @Override
    public PostDto getPost(Long postId) {
        //return postToDto(this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post ID", postId)));
        return null;
    }

    @Override
    public List<PostDto> getAllPosts() {
        return null;
    }

    @Override
    public List<PostDto> getAllPostsByCategoryId(Long categoryId) {
        return null;
    }

    @Override
    public List<PostDto> getAllPostsByCategoryName(String categoryName) {
        return null;
    }

    @Override
    public List<User> getAllPostsByUserId(Long userId) {
        return null;
    }

    @Override
    public List<User> getAllPostsByUserName(String userName) {
        return null;
    }
}

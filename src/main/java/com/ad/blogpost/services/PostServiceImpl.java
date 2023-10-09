package com.ad.blogpost.services;

import com.ad.blogpost.entities.Category;
import com.ad.blogpost.entities.Post;
import com.ad.blogpost.entities.User;
import com.ad.blogpost.exceptions.ResourceNotFoundException;
import com.ad.blogpost.payloads.CategoryDto;
import com.ad.blogpost.payloads.PostDto;
import com.ad.blogpost.payloads.PostResponse;
import com.ad.blogpost.repositories.CategoryRepo;
import com.ad.blogpost.repositories.PostRepo;
import com.ad.blogpost.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo catRepo;

    // Model Mapper
    @Autowired
    private ModelMapper modelMapper;
    private CategoryDto categoryToDto(Category category) {
        return this.modelMapper.map(category, CategoryDto.class);
    }
    private Category dtoToCategory(CategoryDto categoryDto) {return this.modelMapper.map(categoryDto, Category.class);}
    private PostDto postToDto(Post post) {
        return this.modelMapper.map(post, PostDto.class);
    }
    private Post dtoToPost(PostDto postDto) {
        return this.modelMapper.map(postDto, Post.class);
    }

    // CREATE POST using USER ID and CATEGORY ID
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

    // UPDATE POST BY POST ID and NEW POST as Response Body
    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = postRepo
                .findById(postId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Update Post","Post ID", postId)
                );

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCategory(dtoToCategory(postDto.getCategory()));
        post.setImageName(postDto.getImageName());

        return postToDto(this.postRepo.save(post));
    }

    // DELETE POST BY POST ID
    @Override
    public void deletePost(Long postId) {
        postRepo.deleteById(postId);
    }

    // GET POST BY POST ID
    @Override
    public PostDto getPost(Long postId) {
        return postToDto(
                this.postRepo
                        .findById(postId)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Post", "Post ID", postId)
                        )
        );
    }

    // GET ALL POSTS
    @Override
    public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = null;

        if (sortDir.equalsIgnoreCase("ASC")) sort = Sort.by(sortBy).ascending();
        else if (sortDir.equalsIgnoreCase("DESC")) sort = Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> pagePost = this.postRepo.findAll(pageable);

        return getPostResponse(pagePost);
    }

    // GET ALL POSTS BY CATEGORY ID
    @Override
    public List<PostDto> getAllPostsByCategoryId(Long categoryId) {
        List<Post> postList = this.postRepo.findByCategoryId(categoryId);
        return postList.stream().map(this::postToDto).collect(Collectors.toList());
    }

    // GET ALL POSTS BY CATEGORY TITLE
    @Override
    public List<PostDto> getAllPostsByCategoryName(String categoryTitle) {
        List<Post> postList = this.postRepo.findByCategoryTitle(categoryTitle);
        return postList.stream().map(this::postToDto).collect(Collectors.toList());
    }

    // GET ALL POSTS BY USER ID
    @Override
    public List<PostDto> getAllPostsByUserId(Long userId) {
        List<Post> postList = this.postRepo.findByUserId(userId);
        return postList.stream().map(this::postToDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsByUserName(String userName) {
        List<Post> postList = this.postRepo.findByUserName(userName);
        return postList.stream().map(this::postToDto).collect(Collectors.toList());
    }

    @Override
    public PostResponse searchPostsByTitle(String keyword, int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Post> postPage = this.postRepo.findByTitleContainingIgnoreCase(pageable, keyword);

        return getPostResponse(postPage);
    }

    @Override
    public PostResponse searchPostsByContent(String keyword, int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Post> postPage = this.postRepo.searchByContentContainingIgnoreCase(pageable, keyword);

        return getPostResponse(postPage);
    }

    private PostResponse getPostResponse(Page<Post> postPage) {
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postPage.getContent().stream().map(this::postToDto).collect(Collectors.toList()));
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setLastPage(postPage.isLast());
        postResponse.setHasNextPage(postPage.hasNext());
        return postResponse;
    }
}

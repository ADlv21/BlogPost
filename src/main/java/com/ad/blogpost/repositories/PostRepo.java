package com.ad.blogpost.repositories;

import com.ad.blogpost.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {

    List<Post> findByCategoryId(Long categoryId);

    List<Post> findByUserId(Long userId);

    List<Post> findByUserName(String userName);

    List<Post> findByCategoryTitle(String categoryName);
}

package com.ad.blogpost.repositories;

import com.ad.blogpost.entities.Post;
import com.ad.blogpost.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {

    List<Post> findByCategoryId(Long categoryId);

    List<Post> findByUser(User user);

    List<Post> findByUserId(Long userId);
}

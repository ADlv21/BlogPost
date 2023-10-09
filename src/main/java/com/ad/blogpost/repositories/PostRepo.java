package com.ad.blogpost.repositories;

import com.ad.blogpost.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {

    List<Post> findByCategoryId(Long categoryId);

    List<Post> findByUserId(Long userId);

    List<Post> findByUserName(String userName);

    List<Post> findByCategoryTitle(String categoryName);

    Page<Post> findByTitleContainingIgnoreCase(Pageable pageable, String keyword);

    @Query("SELECT p FROM Post p WHERE p.content LIKE %:key%")
    Page<Post> searchByContentContainingIgnoreCase(Pageable pageable, @Param("key")  String content);
}

package com.ad.blogpost.repositories;

import com.ad.blogpost.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Long> {

}

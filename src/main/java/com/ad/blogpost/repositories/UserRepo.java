package com.ad.blogpost.repositories;

import com.ad.blogpost.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findAllByName(String name);
}

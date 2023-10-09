package com.ad.blogpost.repositories;

import com.ad.blogpost.entities.Post;
import com.ad.blogpost.entities.User;
import javafx.geometry.Pos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    List<Post> postList;

    @Test
    void findAllByName() {
        User expected = new User(54L,"Test","trst@mail.com","12345Test","About Test", postList);
        userRepo.save(expected);
        List<User> result = userRepo.findAllByName("Test");
        assertThat(result).isEqualTo(result);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tear Down");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Setting Up");
    }
}

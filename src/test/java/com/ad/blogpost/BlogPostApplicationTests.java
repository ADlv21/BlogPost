package com.ad.blogpost;

import com.ad.blogpost.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class BlogPostApplicationTests {

    @Autowired
    private UserRepo userRepo;

    private final Calculator c = new Calculator();

    @Test
    void contextLoads() {
    }

    @Test
    void testSum(){
        int expected = 26;
        int res = c.doSum(5,12,9);

        assertThat(res).isEqualTo(expected);
    }

    @Test
    void testProduct(){
        int expected = 100;
        int res = c.doProduct(2,5,10);

        assertThat(res).isEqualTo(expected);
    }

    @Test
    void testNums(){
        boolean actual = c.compareTwoNumbers(3,3);
        assertThat(actual).isTrue();
    }

}

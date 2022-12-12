package com.ad.blogpost.repositories;

import com.ad.blogpost.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    // Gets all the info of a specific category
    Category findByTitle(String categoryName);

}

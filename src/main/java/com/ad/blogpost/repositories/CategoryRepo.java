package com.ad.blogpost.repositories;

import com.ad.blogpost.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    Category findByCategoryTitle(String categoryName);
}

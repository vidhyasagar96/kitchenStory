package com.simplilearn.kitchenStory.repository;

import com.simplilearn.kitchenStory.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}


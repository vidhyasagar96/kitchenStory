package com.simplilearn.kitchenStory.repository;

import com.simplilearn.kitchenStory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategoryId(int id);

    List<Product> findByNameStartingWith(String name);

    }

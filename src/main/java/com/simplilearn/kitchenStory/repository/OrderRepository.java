package com.simplilearn.kitchenStory.repository;

import com.simplilearn.kitchenStory.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

    List<Orders> findByCategoryId(int categoryId);

}

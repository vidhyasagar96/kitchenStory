package com.simplilearn.kitchenStory.service;

import com.simplilearn.kitchenStory.entity.Orders;
import com.simplilearn.kitchenStory.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	public List <Orders> getAllOrders(@Nullable Integer categoryID) {
		if(categoryID != null) {
			return orderRepository.findByCategoryId(categoryID);
		}
		return orderRepository.findAll();
	}
	
	public void saveOrder(Orders order) {
		orderRepository.save(order);
		return;
	}

}

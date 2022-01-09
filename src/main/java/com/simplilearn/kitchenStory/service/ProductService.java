package com.simplilearn.kitchenStory.service;

import com.simplilearn.kitchenStory.entity.Product;
import com.simplilearn.kitchenStory.entity.User;
import com.simplilearn.kitchenStory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	
	public void delProduct(long id) {
		productRepository.deleteById(id);
	}
	
	public Optional<Product> getProduct(long id){
		
		return productRepository.findById(id);
	}
	
	public List<Product> getAllProductsByCategory(int id){
		return productRepository.findAllByCategoryId(id);
	}

	public List<Product> getProductsByName(String name){
		return productRepository.findByNameStartingWith(name);
	}

}

package com.simplilearn.kitchenStory.service;

import com.simplilearn.kitchenStory.entity.Category;
import com.simplilearn.kitchenStory.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {

	final
	CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<Category> getAllCategory(){
		return categoryRepository.findAll();
	}
	
	public void addCategory(Category category){
		categoryRepository.save(category);
	}
	
	public void delCategory(int id){
		categoryRepository.deleteById(id);
	}
	
	public Optional<Category> updateCategory(int id){
		return categoryRepository.findById(id);
	}
}
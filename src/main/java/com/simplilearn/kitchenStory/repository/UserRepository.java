package com.simplilearn.kitchenStory.repository;

import com.simplilearn.kitchenStory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);

    List<User> findByNameStartingWith(String name);

}
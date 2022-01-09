package com.simplilearn.kitchenStory.repository;

import com.simplilearn.kitchenStory.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}

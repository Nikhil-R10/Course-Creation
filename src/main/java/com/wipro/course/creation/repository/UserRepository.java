package com.wipro.course.creation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.course.creation.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}

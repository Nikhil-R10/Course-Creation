package com.wipro.course.creation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wipro.course.creation.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	@Query(nativeQuery = true, value = " select * from user where user_name = :userName and logged_in = true")
	User findLoggedInUser(@Param("userName") String userName);

}

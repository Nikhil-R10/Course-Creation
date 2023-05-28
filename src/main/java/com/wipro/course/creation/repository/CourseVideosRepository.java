package com.wipro.course.creation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.course.creation.entity.CourseVideos;

@Repository
public interface CourseVideosRepository extends JpaRepository<CourseVideos, Integer>{

}

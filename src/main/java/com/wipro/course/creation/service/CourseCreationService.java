package com.wipro.course.creation.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.wipro.course.creation.model.Courses;

public interface CourseCreationService {
	
	ResponseEntity<String> createCourse(Courses courses, String userId);
	
	ResponseEntity<String> addCourseDetails(Courses courses, String userId);
	
	ResponseEntity<String> editCourseDetails(Courses courses, String userId);
	
	ResponseEntity<String> addCourseVideos(MultipartFile file, String userId, String courseName);

}

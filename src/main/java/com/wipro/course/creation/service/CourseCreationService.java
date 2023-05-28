package com.wipro.course.creation.service;

import org.springframework.stereotype.Service;

@Service
public interface CourseCreationService {
	
	String createCourse();
	
	String addCourseDetails();
	
	String editCourseDetails();
	
	String addCourseVideos();

}

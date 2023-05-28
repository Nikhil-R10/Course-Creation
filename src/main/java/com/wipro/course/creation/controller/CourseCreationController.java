package com.wipro.course.creation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wipro.course.creation.model.Courses;
import com.wipro.course.creation.service.CourseCreationService;

import static com.wipro.course.creation.constants.CourseConstants.USER_ID;

@RestController
public class CourseCreationController {

	@Autowired
	private CourseCreationService courseCreationService;

	@PostMapping("/create")
	public ResponseEntity<String> createCourse(@RequestBody Courses courses, HttpServletRequest request) {
		String userId = request.getHeader(USER_ID);
		return courseCreationService.createCourse(courses, userId);
	}

	@PostMapping("/add")
	public ResponseEntity<String> addCourseDetails(@RequestBody Courses courses, HttpServletRequest request) {
		String userId = request.getHeader(USER_ID);
		return courseCreationService.addCourseDetails(courses, userId);
	}

	@PostMapping("/edit")
	public ResponseEntity<String> editCourseDetails(@RequestBody Courses courses, HttpServletRequest request) {
		String userId = request.getHeader(USER_ID);
		return courseCreationService.editCourseDetails(courses, userId);
	}

	@PostMapping("/add/video")
	public ResponseEntity<String> addCourseVideo(@RequestParam("video") MultipartFile file,
			@RequestParam("courseName") String courseName, HttpServletRequest request) {
		String userId = request.getHeader(USER_ID);
		return courseCreationService.addCourseVideos(file, userId, courseName);
	}

}

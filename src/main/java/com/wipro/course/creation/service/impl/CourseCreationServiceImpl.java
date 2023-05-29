package com.wipro.course.creation.service.impl;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wipro.course.creation.entity.Course;
import com.wipro.course.creation.entity.CourseVideos;
import com.wipro.course.creation.entity.User;
import com.wipro.course.creation.model.Courses;
import com.wipro.course.creation.repository.CourseRepository;
import com.wipro.course.creation.repository.CourseVideosRepository;
import com.wipro.course.creation.repository.UserRepository;
import com.wipro.course.creation.service.CourseCreationService;

import static com.wipro.course.creation.constants.CourseConstants.SUCCESS;
import static com.wipro.course.creation.constants.CourseConstants.USER_DIRECTORY;
import static com.wipro.course.creation.constants.CourseConstants.LOGIN_TO_CREATE_COURSES;
import static com.wipro.course.creation.constants.CourseConstants.NO_COURSES_FOUND;
import static com.wipro.course.creation.constants.CourseConstants.FILE_SEPARATOR;

@Service
public class CourseCreationServiceImpl implements CourseCreationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseVideosRepository courseVideosRepository;

	@Override
	public ResponseEntity<String> createCourse(Courses courses, String userId) {
		User user = userRepository.findLoggedInUser(userId);
		if (null != user) {
			Course course = new Course();
			course.setName(courses.getName());
			course.setCost(courses.getCost());
			course.setCreatedAt(LocalDateTime.now());
			course.setCreatedBy(userId);
			course.setUpdatedAt(LocalDateTime.now());
			courseRepository.save(course);
			return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<>(LOGIN_TO_CREATE_COURSES, HttpStatus.FORBIDDEN);
	}

	@Override
	public ResponseEntity<String> addCourseDetails(Courses courses, String userId) {
		User user = userRepository.findLoggedInUser(userId);
		Course course = courseRepository.findByName(courses.getName());
		if (user != null) {
			if (course != null) {
				course.setDescription(courses.getDescription());
				course.setCost(courses.getCost());
				course.setLanguages(courses.getLanguages());
				course.setUpdatedAt(LocalDateTime.now());
				courseRepository.save(course);
				return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<>(NO_COURSES_FOUND, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(LOGIN_TO_CREATE_COURSES, HttpStatus.FORBIDDEN);
	}

	@Override
	public ResponseEntity<String> editCourseDetails(Courses courses, String userId) {
		User user = userRepository.findLoggedInUser(userId);
		Course course = courseRepository.findByName(courses.getName());
		if (user != null) {
			if (course != null) {
				course.setName(courses.getName());
				course.setDescription(courses.getDescription());
				course.setCost(courses.getCost());
				course.setLanguages(courses.getLanguages());
				course.setUpdatedAt(LocalDateTime.now());
				courseRepository.save(course);
				return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<>(NO_COURSES_FOUND, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(LOGIN_TO_CREATE_COURSES, HttpStatus.FORBIDDEN);
	}

	@Override
	public ResponseEntity<String> addCourseVideos(MultipartFile file, String userId, String courseName) {
		try {
			User user = userRepository.findLoggedInUser(userId);
			Course course = courseRepository.findByName(courseName);
			if (user != null) {
				if (course != null) {
					CourseVideos video = new CourseVideos();
					createFileStorage(userId, course.getName());
					video.setCourseId(course);
					video.setFileName(file.getOriginalFilename());
					file.transferTo(Path.of(new File(System.getProperty(USER_DIRECTORY)).getParent() + FILE_SEPARATOR
							+ "Course Videos" + FILE_SEPARATOR + userId + FILE_SEPARATOR + course.getName()
							+ FILE_SEPARATOR + file.getOriginalFilename()));
					courseVideosRepository.save(video);
					return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
				}
				return new ResponseEntity<>(NO_COURSES_FOUND, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(LOGIN_TO_CREATE_COURSES, HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Video saving failed! Please try again", HttpStatus.BAD_REQUEST);
		}
	}

	private void createFileStorage(String userId, String courseName) {
		File root = new File(System.getProperty(USER_DIRECTORY));
		File directory = new File(root.getParent() + FILE_SEPARATOR + "Course Videos" + FILE_SEPARATOR + userId
				+ FILE_SEPARATOR + courseName);
		if (!directory.exists())
			directory.mkdirs();
	}

}

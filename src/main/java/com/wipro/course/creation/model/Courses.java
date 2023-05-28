package com.wipro.course.creation.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Courses {
	
	private Integer id;
	private String name;
	private String description;
	private String languages;
	private String cost;
	private String createdBy;
	private LocalDateTime createdAt;

}

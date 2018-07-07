package com.university.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.model.Course;
import com.university.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	CourseRepository courseRepository;

	@GetMapping("/all")
	public List<Course> getCourses(){
		return courseRepository.findAll();
	}
	
}

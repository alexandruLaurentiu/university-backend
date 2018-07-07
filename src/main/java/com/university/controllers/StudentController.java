package com.university.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.exceptions.ResourceNotFoundException;
import com.university.model.Student;
import com.university.repositories.StudentRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/all")
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	@PostMapping("/new")
	public Student createStudent(@Valid @RequestBody Student student) {
		return studentRepository.save(student);
	}

	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable(value = "id") Long studentId) {
		return studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
	}

	@PutMapping("/update/{id}")
	public Student updateStudent(@PathVariable(value = "id") Long studentId,
			@Valid @RequestBody Student studentDetails) {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));

		student.setFirstName(studentDetails.getFirstName());
		student.setLastName(studentDetails.getLastName());

		Student updatedStudent = studentRepository.save(student);
		return updatedStudent;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") Long studentId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));

		studentRepository.delete(student);

		return ResponseEntity.ok().build();
	}

}

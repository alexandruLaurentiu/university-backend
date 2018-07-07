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
import com.university.model.Professor;
import com.university.repositories.ProfessorRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/professors")
public class ProfessorController {

	@Autowired
	ProfessorRepository professorRepository;

	@GetMapping("/all")
	public List<Professor> getProfessors() {
		return professorRepository.findAll();
	}

	@PostMapping("/new")
	public Professor createProfessor(@Valid @RequestBody Professor professor) {
		return professorRepository.save(professor);
	}

	@GetMapping("/{id}")
	public Professor getProfessorById(@PathVariable(value = "id") Long professorId) {
		return professorRepository.findById(professorId)
				.orElseThrow(() -> new ResourceNotFoundException("Professor", "id", professorId));
	}

	@PutMapping("/update/{id}")
	public Professor updateProfessor(@PathVariable(value = "id") Long professorId,
			@Valid @RequestBody Professor professorDetails) {

		Professor professor = professorRepository.findById(professorId)
				.orElseThrow(() -> new ResourceNotFoundException("Professor", "id", professorId));

		professor.setFirstName(professorDetails.getFirstName());
		professor.setLastName(professorDetails.getLastName());

		Professor updatedProfessor = professorRepository.save(professor);
		return updatedProfessor;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProfessor(@PathVariable(value = "id") Long professorId) {
		Professor professor = professorRepository.findById(professorId)
				.orElseThrow(() -> new ResourceNotFoundException("Professor", "id", professorId));

		professorRepository.delete(professor);

		return ResponseEntity.ok().build();
	}

}

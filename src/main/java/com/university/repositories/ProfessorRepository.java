package com.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.university.model.Professor;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}

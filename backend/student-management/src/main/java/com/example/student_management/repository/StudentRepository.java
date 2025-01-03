package com.example.student_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student_management.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Custom method to search students by name (case-insensitive)
    List<Student> findByNameIgnoreCase(String name);
}

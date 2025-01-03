package com.example.student_management.service;


import java.util.List;

import com.example.student_management.model.Student;

public interface StudentService {

    // Add a new student
    Student addStudent(Student student);

    // Get all students
    List<Student> getAllStudents();

    // Search students by name
    List<Student> searchStudentsByName(String name);

    // Update a student
    Student updateStudent(Long id, Student student);

    // Delete a student
    void deleteStudent(Long id);
}

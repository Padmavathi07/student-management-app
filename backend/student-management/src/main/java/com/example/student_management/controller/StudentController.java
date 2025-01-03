package com.example.student_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.student_management.model.Student;
import com.example.student_management.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	// Add a new student
	@PostMapping
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		Student newStudent = studentService.addStudent(student);
		return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
	}

	// Get all students
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> students = studentService.getAllStudents();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	// Search students by name
	@GetMapping("/search")
	public ResponseEntity<List<Student>> searchStudentsByName(@RequestParam String name) {
		List<Student> students = studentService.searchStudentsByName(name);
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	// Update a student
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
		Student updatedStudent = studentService.updateStudent(id, student);
		return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
	}

	// Delete a student
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// json body

	/**
	 * { "name": "Veena", "age": "16", "studentClass": "9th Grade", "phoneNumber":
	 * "9876543220" } { "name": "Vamsi", "age": "17", "studentClass": " 10th Grade",
	 * "phoneNumber": "9876543221" }, { "name": "Surya", "age": "19",
	 * "studentClass": " 12th Grade", "phoneNumber": "9876543222" }
	 */
}

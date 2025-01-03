package com.example.student_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student_management.model.Student;
import com.example.student_management.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public List<Student> searchStudentsByName(String name) {
		return studentRepository.findByNameIgnoreCase(name);
	}

	@Override
	public Student updateStudent(Long id, Student student) {
		Optional<Student> existingStudent = studentRepository.findById(id);
		if (existingStudent.isPresent()) {
			Student updatedStudent = existingStudent.get();
			updatedStudent.setName(student.getName());
			updatedStudent.setAge(student.getAge());
			updatedStudent.setStudentClass(student.getStudentClass());
			updatedStudent.setPhoneNumber(student.getPhoneNumber());
			return studentRepository.save(updatedStudent);
		} else {
			throw new RuntimeException("Student with ID " + id + " not found");
		}
	}

	@Override
	public void deleteStudent(Long id) {
		if (studentRepository.existsById(id)) {
			studentRepository.deleteById(id);
		} else {
			throw new RuntimeException("Student with ID " + id + " not found");
		}
	}
}

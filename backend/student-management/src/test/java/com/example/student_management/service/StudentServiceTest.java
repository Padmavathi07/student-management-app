package com.example.student_management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.student_management.model.Student;
import com.example.student_management.repository.StudentRepository;

class StudentServiceTest {

	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	private StudentServiceImpl studentService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddStudent() {
		Student student = new Student(1L,"John Doe", "15", "10th Grade", "9876543210");
		when(studentRepository.save(student)).thenReturn(student);

		Student result = studentService.addStudent(student);
		assertNotNull(result);
		assertEquals("John Doe", result.getName());
		verify(studentRepository, times(1)).save(student);
	}

	@Test
	void testGetAllStudents() {
		List<Student> students = Arrays.asList(new Student(1L,"John Doe", "15", "10th Grade", "9876543210"),
				new Student(2L,"John Doe", "15", "10th Grade", "9876543210"));
		when(studentRepository.findAll()).thenReturn(students);

		List<Student> result = studentService.getAllStudents();
		assertEquals(2, result.size());
		verify(studentRepository, times(1)).findAll();
	}

	@Test
	void testSearchStudentsByName() {
		List<Student> students = List.of(new Student(1L,"John Doe", "15", "10th Grade", "9876543210"));
		when(studentRepository.findByNameIgnoreCase("John")).thenReturn(students);

		List<Student> result = studentService.searchStudentsByName("John");
		assertEquals(1, result.size());
		verify(studentRepository, times(1)).findByNameIgnoreCase("John");
	}

	@Test
	void testUpdateStudent() {
		Student existingStudent = new Student(1L,"John Doe", "15", "10th Grade", "9876543210");
		existingStudent.setId(1L);

		Student updatedData = new Student(1L,"John Doe", "15", "10th Grade", "9876543210");

		when(studentRepository.findById(1L)).thenReturn(Optional.of(existingStudent));
		when(studentRepository.save(existingStudent)).thenReturn(existingStudent);

		Student result = studentService.updateStudent(1L, updatedData);

		assertNotNull(result);
		assertEquals("John Smith", result.getName());
		assertEquals("16", result.getAge());
		verify(studentRepository, times(1)).findById(1L);
		verify(studentRepository, times(1)).save(existingStudent);
	}

	@Test
	void testDeleteStudent() {
		when(studentRepository.existsById(1L)).thenReturn(true);

		studentService.deleteStudent(1L);

		verify(studentRepository, times(1)).deleteById(1L);
	}
}

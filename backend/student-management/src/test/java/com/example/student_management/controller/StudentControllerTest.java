package com.example.student_management.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.student_management.model.Student;
import com.example.student_management.service.StudentService;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @Test
    void testAddStudent() throws Exception {
        Student student = new Student(1L,"John Doe", "15", "10th Grade", "9876543210");

        when(studentService.addStudent(Mockito.any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1L\",\"name\":\"John Doe\",\"age\":\"15\",\"studentClass\":\"10th Grade\",\"phoneNumber\":9876543210}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("John Doe")));

        verify(studentService, times(1)).addStudent(Mockito.any(Student.class));
    }

    @Test
    void testGetAllStudents() throws Exception {
        List<Student> students = Arrays.asList(
                new Student(1L,"John Doe", "15", "10th Grade", "9876543210"),
                new Student(2L,"John Doe", "15", "10th Grade", "9876543210")
        );

        when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("John Doe")))
                .andExpect(jsonPath("$[1].name", is("Jane Smith")));

        verify(studentService, times(1)).getAllStudents();
    }
}

package com.witnes.SpringSecEx.controller;

import com.witnes.SpringSecEx.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // Create an ArrayList of Student objects
    private List<Student> studentList;

    // Constructor to initialize and populate the student list
    public StudentController() {
        studentList = new ArrayList<>();
        studentList.add(new Student(1, "John Doe", "85"));
        studentList.add(new Student(2, "Jane Smith", "90"));
        studentList.add(new Student(3, "Michael Brown", "78"));
    }

    // REST API to get all students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentList;
    }

    @GetMapping("/crf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
                return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("Students")
    public Student addStudent(@RequestBody Student student){
        studentList.add(student);
        return student;
    }
}

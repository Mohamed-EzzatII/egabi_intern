package com.example.demo.controller;

import com.example.demo.DTO.StudentDTO;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4100") // Angular app
public class LoginController {

    private final StudentService studentService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public LoginController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/login")
    public String login(@RequestBody StudentDTO studentDTO) {
        return studentService.login(studentDTO.getUsername(), studentDTO.getPassword());
    }

    @PostMapping("/register")
    public StudentDTO addStudent(@RequestBody StudentDTO student) {
        System.out.println(student.toString());
        Student student1 = studentService.addNewStudent(student);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentName(student1.getStudent_name());
        studentDTO.setStudentLevel(student1.getStudent_level());
        studentDTO.setFacultyName(student1.getFaculty().getFacultyName());
        studentDTO.setUsername(student1.getUsername());
        return studentDTO;
    }
}

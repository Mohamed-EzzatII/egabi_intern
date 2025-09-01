package com.example.demo.controller;

import com.example.demo.DTO.StudentDTO;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:4100") // Angular app
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/list")
    public List<StudentDTO> getAllStudents() {
        List<Student> studentList = studentService.getAllStudents();
        List<StudentDTO> studentDTOList = new ArrayList<>();
        int count = 0;
        for (Student student : studentList) {
            studentDTOList.add(new StudentDTO());
            studentDTOList.get(count).setStudentName(student.getStudent_name());
            studentDTOList.get(count).setStudentLevel(student.getStudent_level());
            studentDTOList.get(count).setFacultyName(student.getFaculty().getFacultyName());
            count++;
        }
        return studentDTOList;
    }

    @GetMapping("/list/faculty/{faculty_name}")
    public List<StudentDTO> getAllStudents(@PathVariable String faculty_name) {
        List<Student> studentList = studentService.getAllStudentsByFaculty(faculty_name);
        List<StudentDTO> studentDTOList = new ArrayList<>();
        int count = 0;
        for (Student student : studentList) {
            studentDTOList.add(new StudentDTO());
            studentDTOList.get(count).setStudentName(student.getStudent_name());
            studentDTOList.get(count).setStudentLevel(student.getStudent_level());
            studentDTOList.get(count).setFacultyName(student.getFaculty().getFacultyName());
            count++;
        }
        return studentDTOList;
    }
    @GetMapping("/list/level/{level}")
    public List<StudentDTO> getAllStudents(@PathVariable Integer level) {
        List<Student> studentList = studentService.getAllStudentsByLevel(level);
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for (Student student : studentList) {
            studentDTOList.add(new StudentDTO());
            studentDTOList.getFirst().setStudentName(student.getStudent_name());
            studentDTOList.getFirst().setStudentLevel(student.getStudent_level());
            studentDTOList.getFirst().setFacultyName(student.getFaculty().getFacultyName());
        }
        return studentDTOList;
    }

    @GetMapping("/count")
    public double countStudents() {
        return studentService.countStudents();
    }



    @GetMapping("/find/name/{name}")
    public List<StudentDTO> findStudent(@PathVariable String name) {
        List <Student> students= studentService.findStudentByName(name);
        List<StudentDTO> studentDTOList = new ArrayList<>();
        int count = 0;
        for (Student student : students) {
            studentDTOList.add(new StudentDTO());
            studentDTOList.get(count).setStudentName(student.getStudent_name());
            studentDTOList.get(count).setStudentLevel(student.getStudent_level());
            studentDTOList.get(count).setFacultyName(student.getFaculty().getFacultyName());
            count++;
        }
        return studentDTOList;
    }

    @GetMapping("/find/id/{id}")
    public StudentDTO findStudent(@PathVariable Integer id) {
        Student student = studentService.findStudentById(id);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentName(student.getStudent_name());
        studentDTO.setStudentLevel(student.getStudent_level());
        studentDTO.setFacultyName(student.getFaculty().getFacultyName());
        return studentDTO;
    }

    @DeleteMapping("/delete/id/{id}")
    public StudentDTO deleteStudent(@PathVariable Integer id) {
        Student student = studentService.deleteStudentById(id);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentName(student.getStudent_name());
        studentDTO.setStudentLevel(student.getStudent_level());
        studentDTO.setFacultyName(student.getFaculty().getFacultyName());
        return studentDTO;
    }

    @DeleteMapping("/delete/name/{name}")
    public StudentDTO deleteStudent(@PathVariable String name) {
        Student student = studentService.deleteStudentByName(name);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentName(student.getStudent_name());
        studentDTO.setStudentLevel(student.getStudent_level());
        studentDTO.setFacultyName(student.getFaculty().getFacultyName());
        return studentDTO;
    }

    @PutMapping("/update/{id}")
    public StudentDTO updateStudent(@PathVariable Integer id, @RequestBody StudentDTO student) {
        Student student1 = studentService.updateStudentById(id, student);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentName(student1.getStudent_name());
        studentDTO.setStudentLevel(student1.getStudent_level());
        studentDTO.setFacultyName(student1.getFaculty().getFacultyName());
        return studentDTO;
    }
}
package com.example.demo.controller;
import com.example.demo.DTO.StudentDTO;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.web.bind.annotation.*;
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
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/list/faculty/{faculty_name}")
    public List<Student> getAllStudents(@PathVariable String faculty_name) {
        return studentService.getAllStudentsByFaculty(faculty_name);
    }
    @GetMapping("/list/level/{level}")
    public List<Student> getAllStudents(@PathVariable Integer level) {
        return studentService.getAllStudentsByLevel(level);
    }

    @GetMapping("/count")
    public double countStudents() {
        return studentService.countStudents();
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody StudentDTO student) {
        System.out.println(student.toString());
        return studentService.addNewStudent(student);
    }

    @GetMapping("/find/name/{name}")
    public List<Student> findStudent(@PathVariable String name) {
        return studentService.findStudentByName(name);
    }

    @GetMapping("/find/id/{id}")
    public Student findStudent(@PathVariable Integer id) {
        return studentService.findStudentById(id);
    }

    @DeleteMapping("/delete/id/{id}")
    public Student deleteStudent(@PathVariable Integer id) {
        return studentService.deleteStudentById(id);
    }

    @DeleteMapping("/delete/name/{name}")
    public Student deleteStudent(@PathVariable String name) {
        return studentService.deleteStudentByName(name);
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody StudentDTO student) {
        return studentService.updateStudentById(id, student);
    }

}
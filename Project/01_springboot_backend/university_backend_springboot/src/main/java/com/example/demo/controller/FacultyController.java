package com.example.demo.controller;

import com.example.demo.DTO.FacultyDTO;
import com.example.demo.DTO.CourseDTO;
import com.example.demo.model.Faculty;
import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.service.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculties")
@CrossOrigin(origins = "http://localhost:4100") // Angular app
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/list")
    public List<Faculty> getAllFaculties() {
        return facultyService.getAllFaculties();
    }

    @GetMapping("/list/courses/{facultyId}")
    public List<Course> getAllCoursesByFaculty(@PathVariable Integer facultyId) {
        return facultyService.getAllCoursesByFaculty(facultyId);
    }

    @GetMapping("/list/students/{facultyId}")
    public List<Student> getAllStudentsByFaculty(@PathVariable Integer facultyId) {
        return facultyService.getAllStudentsByFaculty(facultyId);
    }

    @GetMapping("/count")
    public long countFaculties() {
        return facultyService.countFaculties();
    }

    @PostMapping("/add")
    public Faculty addFaculty(@RequestBody FacultyDTO facultyDTO) {
        System.out.println(facultyDTO.toString());
        return facultyService.addNewFaculty(facultyDTO);
    }

    @GetMapping("/find/name/{name}")
    public List<Faculty> findFaculty(@PathVariable String name) {
        return facultyService.findFacultyByName(name);
    }

    @GetMapping("/find/id/{id}")
    public Faculty findFaculty(@PathVariable Integer id) {
        return facultyService.findFacultyById(id);
    }

    @DeleteMapping("/delete/id/{id}")
    public Faculty deleteFaculty(@PathVariable Integer id) {
        return facultyService.deleteFacultyById(id);
    }

    @DeleteMapping("/delete/name/{name}")
    public Faculty deleteFaculty(@PathVariable String name) {
        return facultyService.deleteFacultyByName(name);
    }

    @PutMapping("/update/{id}")
    public Faculty updateFaculty(@PathVariable Integer id, @RequestBody FacultyDTO facultyDTO) {
        return facultyService.updateFacultyById(id, facultyDTO);
    }

    @PostMapping("/{facultyId}/courses/add")
    public Faculty addCourseToFaculty(@PathVariable Integer facultyId, @RequestBody CourseDTO courseDTO) {
        return facultyService.addCourseToFaculty(facultyId, courseDTO);
    }


}
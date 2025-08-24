package com.example.demo.controller;

import com.example.demo.DTO.CourseDTO;
import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "http://localhost:4100") // Angular app
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/list")
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/list/faculty/{facultyId}")
    public List<Course> getCoursesByFaculty(@PathVariable Integer facultyId) {
        return courseService.findCoursesByFaculty(facultyId);
    }

    @GetMapping("/list/level/{minLevel}")
    public List<CourseDTO> getCoursesByMinLevel(@PathVariable Integer minLevel) {
        return courseService.findCoursesByMinLevel(minLevel);
    }

    @GetMapping("/count")
    public long countCourses() {
        return courseService.countCourses();
    }

    @PostMapping("/add")
    public Course addCourse(@RequestBody CourseDTO courseDTO) {
        System.out.println(courseDTO.toString());
        return courseService.addNewCourse(courseDTO);
    }

    @GetMapping("/find/name/{name}")
    public List<CourseDTO> findCourse(@PathVariable String name) {
        return courseService.findCourseByName(name);
    }

    @GetMapping("/find/id/{id}")
    public Course findCourse(@PathVariable Integer id) {
        return courseService.findCourseById(id);
    }

    @DeleteMapping("/delete/id/{id}")
    public Course deleteCourse(@PathVariable Integer id) {
        return courseService.deleteCourseById(id);
    }

    @DeleteMapping("/delete/name/{name}")
    public Course deleteCourse(@PathVariable String name) {
        return courseService.deleteCourseByName(name);
    }

    @PutMapping("/update/{id}")
    public Course updateCourse(@PathVariable Integer id, @RequestBody CourseDTO courseDTO) {
        return courseService.updateCourseById(id, courseDTO);
    }
}
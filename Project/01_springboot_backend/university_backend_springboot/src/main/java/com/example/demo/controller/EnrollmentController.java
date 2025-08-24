package com.example.demo.controller;

import com.example.demo.DTO.EnrollmentDTO;
import com.example.demo.DTO.EnrollmentResDTO;
import com.example.demo.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enrollments")
@CrossOrigin(origins = "http://localhost:4100") // Angular app
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/enroll")
    public EnrollmentResDTO enrollStudent(@RequestBody EnrollmentDTO enrollmentDTO) {
        return enrollmentService.enrollStudent(enrollmentDTO);
    }
    @PostMapping("/enroll/student/{stname}/course/{coursename}")
    public EnrollmentResDTO enrollStudentName(@PathVariable String stname, @PathVariable String coursename) {
        return enrollmentService.enrollStudentByName(stname,coursename);
    }

    @PutMapping("/grade")
    public EnrollmentResDTO updateGrade(@RequestBody EnrollmentDTO enrollmentDTO) {
        return enrollmentService.updateGrade(enrollmentDTO);
    }

    @DeleteMapping("/unenroll/course/{courseId}/student/{studentId}")
    public void unenrollStudent(
            @PathVariable Integer courseId,
            @PathVariable Integer studentId) {
        enrollmentService.unenrollStudent(courseId, studentId);
    }

    @GetMapping("/student/{studentId}")
    public List<EnrollmentResDTO> getStudentEnrollments(@PathVariable Integer studentId) {
        return enrollmentService.getEnrollmentsByStudent(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<EnrollmentResDTO> getCourseEnrollments(@PathVariable Integer courseId) {
        return enrollmentService.getEnrollmentsByCourse(courseId);
    }

    @GetMapping("/grade/course/{courseId}/student/{studentId}")
    public Optional<Double> getStudentGrade(
            @PathVariable Integer courseId,
            @PathVariable Integer studentId) {
        return enrollmentService.getStudentGrade(courseId, studentId);
    }

    @GetMapping("/list")
    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }
}
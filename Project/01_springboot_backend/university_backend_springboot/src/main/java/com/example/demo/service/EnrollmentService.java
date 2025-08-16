package com.example.demo.service;

import com.example.demo.DTO.EnrollmentDTO;
import com.example.demo.model.*;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Enrollment enrollStudent(EnrollmentDTO enrollmentDTO) {
        Student student = studentRepository.findById(enrollmentDTO.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Course course = courseRepository.findById(enrollmentDTO.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        // Check if student meets course level requirement
        if (student.getStudent_level() < course.getMinLevel()) {
            throw new IllegalArgumentException("Student level too low for this course");
        }

        // Check if enrollment already exists
        if (enrollmentRepository.existsById(new EnrollmentId(enrollmentDTO.getCourseId(), enrollmentDTO.getStudentId()))) {
            throw new IllegalArgumentException("Student already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setId(new EnrollmentId(enrollmentDTO.getCourseId(), enrollmentDTO.getStudentId()));
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        if (enrollmentDTO.getGrade() != null) {
            enrollment.setGrade(enrollmentDTO.getGrade().doubleValue());
        }

        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public Enrollment updateGrade(EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = enrollmentRepository.findById(
                        new EnrollmentId(enrollmentDTO.getCourseId(), enrollmentDTO.getStudentId()))
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));

        if (enrollmentDTO.getGrade() != null) {
            enrollment.setGrade(enrollmentDTO.getGrade().doubleValue());
        }

        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public void unenrollStudent(Integer courseId, Integer studentId) {
        enrollmentRepository.deleteById(new EnrollmentId(courseId, studentId));
    }

    public List<Enrollment> getEnrollmentsByStudent(Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        return enrollmentRepository.findByStudent(student);
    }

    public List<Enrollment> getEnrollmentsByCourse(Integer courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        return enrollmentRepository.findByCourse(course);
    }

    public Optional<Double> getStudentGrade(Integer courseId, Integer studentId) {
        return enrollmentRepository.findById(new EnrollmentId(courseId, studentId))
                .map(Enrollment::getGrade);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }
}
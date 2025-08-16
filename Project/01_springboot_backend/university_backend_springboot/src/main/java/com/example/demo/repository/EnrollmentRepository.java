package com.example.demo.repository;

import com.example.demo.model.Course;
import com.example.demo.model.Enrollment;
import com.example.demo.model.EnrollmentId;
import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment,EnrollmentId> {
    List<Enrollment> findByStudent(Student student);
    List<Enrollment> findByCourse(Course course);
}

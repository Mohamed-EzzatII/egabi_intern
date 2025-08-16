package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByStudentName(String student_name);
    List<Student> findByStudentNameContainingIgnoreCase(String student_name);
    void deleteById(Integer id);
}

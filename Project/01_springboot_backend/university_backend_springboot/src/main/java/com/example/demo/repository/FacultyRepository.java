package com.example.demo.repository;

import com.example.demo.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
    List<Faculty> findByFacultyName(String faculty_name);
    List<Faculty> findByFacultyNameContainingIgnoreCase(String faculty_name);
}

package com.example.demo.repository;

import com.example.demo.model.Course;
import com.example.demo.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    List<Course> findByFaculty(Faculty faculty);
    List<Course> findByCourseNameContainingIgnoreCase( String name);
    List<Course> findByMinLevel(Integer minLevel);
    List<Course> findByCourseNameIgnoreCase( String name);
    List<Course> findByMinLevelLessThanEqual(Integer minLevel);
}

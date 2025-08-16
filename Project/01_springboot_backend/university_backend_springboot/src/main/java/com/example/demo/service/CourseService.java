package com.example.demo.service;

import com.example.demo.DTO.CourseDTO;
import com.example.demo.model.Course;
import com.example.demo.model.Faculty;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final FacultyRepository facultyRepository;

    public CourseService(CourseRepository courseRepository, FacultyRepository facultyRepository) {
        this.courseRepository = courseRepository;
        this.facultyRepository = facultyRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public long countCourses() {
        return courseRepository.count();
    }

    public Course addNewCourse(CourseDTO courseDTO) {
        if (courseDTO.getMinLevel() < 0) {
            System.out.println("Course minimum level cannot be negative");
            return null;
        }

        Faculty faculty = facultyRepository.findById(courseDTO.getFacultyId())
                .orElse(null);

        if (faculty == null) {
            System.out.println("Faculty not found");
            return null;
        }

        Course newCourse = new Course();
        newCourse.setCourseName(courseDTO.getCourseName());
        newCourse.setMinLevel(courseDTO.getMinLevel());
        newCourse.setFaculty(faculty);

        return courseRepository.save(newCourse);
    }

    public List<Course> findCourseByName(String name) {
        return courseRepository.findByCourseNameContainingIgnoreCase(name);
    }

    public Course findCourseById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

    public List<Course> findCoursesByFaculty(Integer facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElse(null);
        if (faculty == null) {
            System.out.println("Faculty not found");
            return null;
        }
        return courseRepository.findByFaculty(faculty);
    }

    public List<Course> findCoursesByMinLevel(Integer minLevel) {
        return courseRepository.findByMinLevel(minLevel);
    }

    public Course deleteCourseById(Integer id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            courseRepository.deleteById(id);
        }
        return course;
    }

    public Course deleteCourseByName(String name) {
        List<Course> courses = courseRepository.findByCourseNameContainingIgnoreCase(name);
        if (courses.isEmpty()) {
            System.out.println("Course not found");
            return null;
        } else if (courses.size() != 1) {
            System.out.println("More than one course found");
            return null;
        }
        courseRepository.deleteById(courses.get(0).getCourseId());
        return courses.get(0);
    }

    public Course updateCourseById(Integer id, CourseDTO courseDTO) {
        Course existingCourse = courseRepository.findById(id).orElse(null);
        if (existingCourse == null) {
            System.out.println("Course not found");
            return null;
        }

        if (courseDTO.getCourseName() != null) {
            existingCourse.setCourseName(courseDTO.getCourseName());
        }

        if (courseDTO.getMinLevel() != null) {
            if (courseDTO.getMinLevel() < 0) {
                System.out.println("Minimum level cannot be negative");
                return null;
            }
            existingCourse.setMinLevel(courseDTO.getMinLevel());
        }

        if (courseDTO.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(courseDTO.getFacultyId())
                    .orElse(null);
            if (faculty == null) {
                System.out.println("Faculty not found");
                return null;
            }
            existingCourse.setFaculty(faculty);
        }

        return courseRepository.save(existingCourse);
    }
}
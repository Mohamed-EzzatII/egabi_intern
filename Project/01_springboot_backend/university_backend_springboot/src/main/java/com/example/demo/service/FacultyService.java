package com.example.demo.service;

import com.example.demo.DTO.CourseDTO;
import com.example.demo.DTO.FacultyDTO;
import com.example.demo.model.Course;
import com.example.demo.model.Faculty;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final CourseRepository courseRepository;

    public FacultyService(FacultyRepository facultyRepository, CourseRepository courseRepository) {
        this.facultyRepository = facultyRepository;
        this.courseRepository = courseRepository;
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public long countFaculties() {
        return facultyRepository.count();
    }

    public Faculty addNewFaculty(FacultyDTO facultyDTO) {
        if (facultyDTO.getFacultyName() == null || facultyDTO.getFacultyName().isEmpty()) {
            System.out.println("Faculty name cannot be empty");
            return null;
        }

        Faculty newFaculty = new Faculty();
        newFaculty.setFacultyName(facultyDTO.getFacultyName());
        newFaculty.setSpecialization(facultyDTO.getSpecialization());
        return facultyRepository.save(newFaculty);
    }

    public List<Faculty> findFacultyByName(String name) {
        return facultyRepository.findByFacultyNameContainingIgnoreCase(name);
    }

    public Faculty findFacultyById(Integer id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty deleteFacultyById(Integer id) {
        Faculty faculty = facultyRepository.findById(id).orElse(null);
        if (faculty != null) {
            facultyRepository.deleteById(id);
        }
        return faculty;
    }

    public Faculty deleteFacultyByName(String name) {
        List<Faculty> faculties = facultyRepository.findByFacultyNameContainingIgnoreCase(name);
        if (faculties.isEmpty()) {
            System.out.println("Faculty not found");
            return null;
        } else if (faculties.size() != 1) {
            System.out.println("More than one faculty found");
            return null;
        }
        facultyRepository.deleteById(faculties.getFirst().getFacultyId());
        return faculties.getFirst();
    }

    public List<Course> getAllCoursesByFaculty(Integer facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElse(null);
        if (faculty == null) {
            System.out.println("Faculty not found");
            return null;
        }
        return courseRepository.findByFaculty(faculty);
    }

    public List<Student> getAllStudentsByFaculty(Integer facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElse(null);
        if (faculty == null) {
            System.out.println("Faculty not found");
            return null;
        }
        return faculty.getStudents();
    }

    public Faculty updateFacultyById(Integer id, FacultyDTO facultyDTO) {
        Faculty oldFaculty = facultyRepository.findById(id).orElse(null);
        if (oldFaculty == null) {
            System.out.println("Faculty not found");
            return null;
        }

        if (facultyDTO.getFacultyName() != null) {
            oldFaculty.setFacultyName(facultyDTO.getFacultyName());
        }
        if (facultyDTO.getSpecialization() != null) {
            oldFaculty.setSpecialization(facultyDTO.getSpecialization());
        }
        return facultyRepository.save(oldFaculty);
    }

    public Faculty addCourseToFaculty(Integer facultyId, CourseDTO courseDTO) {
        Faculty faculty = facultyRepository.findById(facultyId).orElse(null);
        if (faculty == null) {
            System.out.println("Faculty not found");
            return null;
        }

        Course newCourse = new Course();
        newCourse.setCourseName(courseDTO.getCourseName());
        newCourse.setMinLevel(courseDTO.getMinLevel());
        newCourse.setFaculty(faculty);

        courseRepository.save(newCourse);
        return facultyRepository.findById(facultyId).orElse(null);
    }
}
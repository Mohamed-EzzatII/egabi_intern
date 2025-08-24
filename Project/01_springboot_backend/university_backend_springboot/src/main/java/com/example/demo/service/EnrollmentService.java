package com.example.demo.service;

import com.example.demo.DTO.EnrollmentDTO;
import com.example.demo.DTO.EnrollmentResDTO;
import com.example.demo.model.Course;
import com.example.demo.model.Enrollment;
import com.example.demo.model.EnrollmentId;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public EnrollmentResDTO enrollStudent(EnrollmentDTO enrollmentDTO) {
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
        Enrollment newEnroll = enrollmentRepository.save(enrollment);
        EnrollmentResDTO enrollmentResDTO = new EnrollmentResDTO();
        enrollmentResDTO.setStudentName(newEnroll.getStudent().getStudent_name());
        enrollmentResDTO.setCourseName(newEnroll.getCourse().getCourseName());
        enrollmentResDTO.setGrade(newEnroll.getGrade() == null ? 0 : newEnroll.getGrade().intValue());

        return enrollmentResDTO;
    }
    @Transactional
    public EnrollmentResDTO enrollStudentByName(String stname,String coursename) {
        List<Student> students = studentRepository.findByStudentNameIgnoreCase(stname);
        List<Course> courses = courseRepository.findByCourseNameIgnoreCase(coursename);

        Course course = courses.get(0);
        Student student = students.get(0);
        // Check if student meets course level requirement
        if (student.getStudent_level() < course.getMinLevel()) {
            throw new IllegalArgumentException("Student level too low for this course");
        }


        Enrollment enrollment = new Enrollment();
        enrollment.setId(new EnrollmentId(course.getCourseId(), student.getStudent_id()));
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        Enrollment newEnroll = enrollmentRepository.save(enrollment);
        EnrollmentResDTO enrollmentResDTO = new EnrollmentResDTO();
        enrollmentResDTO.setStudentName(newEnroll.getStudent().getStudent_name());
        enrollmentResDTO.setCourseName(newEnroll.getCourse().getCourseName());
        enrollmentResDTO.setGrade(newEnroll.getGrade() == null ? 0 : newEnroll.getGrade().intValue());

        return enrollmentResDTO;
    }

    @Transactional
    public EnrollmentResDTO updateGrade(EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = enrollmentRepository.findById(
                        new EnrollmentId(enrollmentDTO.getCourseId(), enrollmentDTO.getStudentId()))
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));

        if (enrollmentDTO.getGrade() != null) {
            enrollment.setGrade(enrollmentDTO.getGrade().doubleValue());
        }

        Enrollment newEnroll = enrollmentRepository.save(enrollment);
        EnrollmentResDTO enrollmentResDTO = new EnrollmentResDTO();
        enrollmentResDTO.setStudentName(newEnroll.getStudent().getStudent_name());
        enrollmentResDTO.setCourseName(newEnroll.getCourse().getCourseName());
        enrollmentResDTO.setGrade(newEnroll.getGrade() == null ? null : newEnroll.getGrade().intValue());

        return enrollmentResDTO;
    }

    @Transactional
    public void unenrollStudent(Integer courseId, Integer studentId) {
        enrollmentRepository.deleteById(new EnrollmentId(courseId, studentId));
    }

    public List<EnrollmentResDTO> getEnrollmentsByStudent(Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        List<Enrollment> enrollments = enrollmentRepository.findByStudent(student);
        List<EnrollmentResDTO> enrollmentResDTOList = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            EnrollmentResDTO enrollmentResDTO = new EnrollmentResDTO();
            enrollmentResDTO.setStudentName(enrollment.getStudent().getStudent_name());
            enrollmentResDTO.setCourseName(enrollment.getCourse().getCourseName());
            enrollmentResDTO.setGrade(enrollment.getGrade() == null ? null : enrollment.getGrade().intValue());
            enrollmentResDTOList.add(enrollmentResDTO);
        }
        return enrollmentResDTOList;
    }

    public List<EnrollmentResDTO> getEnrollmentsByCourse(Integer courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        List<Enrollment> enrollments = enrollmentRepository.findByCourse(course);
        List<EnrollmentResDTO> enrollmentResDTOList = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            EnrollmentResDTO enrollmentResDTO = new EnrollmentResDTO();
            enrollmentResDTO.setStudentName(enrollment.getStudent().getStudent_name());
            enrollmentResDTO.setCourseName(enrollment.getCourse().getCourseName());
            enrollmentResDTO.setGrade(enrollment.getGrade() == null ? null : enrollment.getGrade().intValue());
            enrollmentResDTOList.add(enrollmentResDTO);
        }
        return enrollmentResDTOList;
    }

    public Optional<Double> getStudentGrade(Integer courseId, Integer studentId) {
        return enrollmentRepository.findById(new EnrollmentId(courseId, studentId))
                .map(Enrollment::getGrade);
    }

    public List<EnrollmentDTO> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        List<EnrollmentDTO> enrollmentDTOList = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
            enrollmentDTO.setStudentName(enrollment.getStudent().getStudent_name());
            enrollmentDTO.setCourseName(enrollment.getCourse().getCourseName());
            enrollmentDTO.setGrade(enrollment.getGrade() == null ? null : enrollment.getGrade().intValue());
            enrollmentDTO.setCourseId(enrollment.getCourse().getCourseId());
            enrollmentDTO.setStudentId(enrollment.getStudent().getStudent_id());
            enrollmentDTOList.add(enrollmentDTO);
        }
        return enrollmentDTOList;
    }
}
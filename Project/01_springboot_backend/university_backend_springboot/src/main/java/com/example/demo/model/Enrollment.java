package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity

@Table(name = "Enrollment")
public class Enrollment {
    @EmbeddedId
    private EnrollmentId id;

    @ManyToOne
    @MapsId("courseId")
    @JsonManagedReference
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JsonManagedReference
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "grade")
    private Double grade;

    // Getters and setters
    public EnrollmentId getId() {
        return id;
    }

    public void setId(EnrollmentId id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        if (grade != null && grade < 0) {
            throw new IllegalArgumentException("grade must be >= 0");
        }
        this.grade = grade;
    }
}
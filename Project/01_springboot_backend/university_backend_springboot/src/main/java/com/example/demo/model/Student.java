package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "student_name", nullable = false, length = 100)
    private String studentName;

    @Column(name = "student_level", nullable = false)
    private Integer studentLevel;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    @JsonManagedReference
    private Faculty faculty;

    // Getters and setters
    public Integer getStudent_id() {
        return studentId;
    }

    public void setStudent_id(Integer student_id) {
        this.studentId = student_id;
    }

    public String getStudent_name() {
        return studentName;
    }

    public void setStudent_name(String student_name) {
        this.studentName = student_name;
    }

    public Integer getStudent_level() {
        return studentLevel;
    }

    public void setStudent_level(Integer student_level) {
        if (student_level < 0) {
            throw new IllegalArgumentException("student_level must be >= 0");
        }
        this.studentLevel = student_level;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
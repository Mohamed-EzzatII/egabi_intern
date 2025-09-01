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

    @Column(name="username" ,nullable = false)
    private String userName;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name = "role",nullable = false)
    private String role; // student or admin

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    @JsonManagedReference
    private Faculty faculty;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStudentLevel() {
        return studentLevel;
    }

    public void setStudentLevel(Integer studentLevel) {
        this.studentLevel = studentLevel;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

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
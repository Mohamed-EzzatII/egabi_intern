package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@SQLDelete(sql = "UPDATE faculty SET deleted = true WHERE faculty_id = ?")
@Where(clause = "deleted = false") // filter queries automatically
@Table(name = "Faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private Integer facultyId;

    @Column(name = "faculty_name", nullable = false, length = 100)
    private String facultyName;

    @Column(name = "specialization", nullable = false, length = 50)
    private String specialization;

    @OneToMany(mappedBy = "faculty")
    @JsonBackReference
    private List<Student> students;

    @JsonBackReference
    @OneToMany(mappedBy = "faculty")
    private List<Course> courses;

    // Getters and setters
    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
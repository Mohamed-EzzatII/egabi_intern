package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "Course")
@SQLDelete(sql = "UPDATE course SET deleted = true WHERE course_id = ?;")
@Where(clause = "deleted = false") // filter queries automatically
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer courseId;

    @Setter
    @Column(name = "course_name", nullable = false, length = 100)
    private String courseName;

    @Column(name = "min_level", nullable = false)
    private Integer minLevel;

    @Setter
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    // Getters and setters
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        if (minLevel < 0) {
            throw new IllegalArgumentException("minLevel must be >= 0");
        }
        this.minLevel = minLevel;
    }

    public Faculty getFaculty() {
        return faculty;
    }

}
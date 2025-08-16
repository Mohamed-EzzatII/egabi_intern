package com.example.demo.DTO;

public class CourseDTO {

    private String courseName;

    private Integer minLevel;

    private String facultyName;

    private Integer facultyId;

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public String getCourseName() {
        return courseName.toLowerCase();
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName.toLowerCase();
    }

    public String getFacultyName() {
        return facultyName.toLowerCase();
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName.toLowerCase();
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }
}

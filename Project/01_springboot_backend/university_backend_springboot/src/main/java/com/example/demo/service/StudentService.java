package com.example.demo.service;

import com.example.demo.DTO.StudentDTO;
import com.example.demo.model.Faculty;
import com.example.demo.model.Student;
import com.example.demo.repository.FacultyRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class StudentService{
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    private JWTService jWTService;
//    private final PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository/*, PasswordEncoder passwordEncoder*/) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public long countStudents(){
        return studentRepository.count();
    }

    public Student addNewStudent(StudentDTO student) {

        if(student.getStudentLevel()<0){
            System.out.println("student's level is less than 0");
            return null;
        }
        List<Faculty> faculty = facultyRepository.findByFacultyNameContainingIgnoreCase(student.getFacultyName());

        if(faculty.isEmpty()){
            System.out.println("faculty is null");
            return null;
        }
        else if(faculty.size()!=1){
            System.out.println("More than one faculty found");
            return null;
        }
        Student newStudent = studentRepository.findByUserName(student.getUsername());
        if(newStudent!=null){
            return null;
        }
        else {
            newStudent = new Student();
            newStudent.setStudent_name(student.getStudentName());
            newStudent.setStudent_level(student.getStudentLevel());
            newStudent.setPassword(encoder.encode(student.getPassword()));
            newStudent.setUsername(student.getUsername());
            newStudent.setRole("ROLE_STUDENT");
            newStudent.setFaculty(faculty.getFirst());
            return studentRepository.save(newStudent);
        }
    }

    public List<Student> findStudentByName(String name) {
        return studentRepository.findByStudentNameContainingIgnoreCase(name);
    }

    public Student findStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    public String login(String username, String password) {
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if (authentication.isAuthenticated()) {
            return jWTService.generateToken(username,studentRepository.findByUserName(username).getRole());
        }
        return "";
    }

    public Student deleteStudentById(Integer id) {
        Student student = studentRepository.findById(id).orElse(null);
        studentRepository.deleteAllById(Collections.singleton(id));
        return student;
    }
    public Student deleteStudentByName(String name) {
        List<Student> students =studentRepository.findByStudentNameContainingIgnoreCase(name);
        if(students.isEmpty()){
            System.out.println("student is null");
            return null;
        }
        else if(students.size()!=1){
            System.out.println("More than one student found");
            return null;
        }
        studentRepository.deleteById(students.getFirst().getStudent_id());
        return students.getFirst();
    }
    public List<Student> getAllStudentsByFaculty(String faculty_name) {
        List<Faculty> faculty = facultyRepository.findByFacultyNameContainingIgnoreCase(faculty_name);
        if(faculty.isEmpty()){
            System.out.println("faculty is null");
            return null;
        }
        else if(faculty.size()!=1){
            System.out.println("More than one faculty found");
            return null;
        }

        else {
            List<Student> students = studentRepository.findAll();
            List<Student> facultyStudents = new java.util.ArrayList<>(List.of());
            Integer faculty_id = faculty.getFirst().getFacultyId();
            for (Student student : students) {
                if (student.getFaculty().getFacultyId().equals(faculty_id)) {
                    facultyStudents.add(student);
                }
            }
            return facultyStudents;
        }
    }
    public List<Student> getAllStudentsByLevel(Integer level) {

        List<Student> students = studentRepository.findAll();
        List<Student> levelStudents = new java.util.ArrayList<>(List.of());
        for(Student student : students){
            if(Objects.equals(student.getStudent_level(), level)){
                levelStudents.add(student);
            }
        }
        return levelStudents;
    }
    public Student updateStudentById(Integer id, StudentDTO student) {
       Student oldStudent  = studentRepository.findById(id).orElse(null);
       List<Faculty> faculties = facultyRepository.findByFacultyNameContainingIgnoreCase(student.getFacultyName());
       if(oldStudent==null){
            System.out.println("student is null");
            return null;
        }
        if (faculties.isEmpty()){
            System.out.println("faculty is null");
            return null;
        }
        if (faculties.size()!=1){
            System.out.println("More than one faculty found");
            return null;
        }

        oldStudent.setStudent_level(student.getStudentLevel());
        oldStudent.setStudent_name(student.getStudentName());
        oldStudent.setFaculty(faculties.getFirst());
        return studentRepository.save(oldStudent);
    }

}
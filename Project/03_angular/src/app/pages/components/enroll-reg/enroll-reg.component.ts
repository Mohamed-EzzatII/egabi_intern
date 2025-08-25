import { Component, OnInit } from '@angular/core';
import { StudentsService } from '../../service/students/students.service';
import { CoursesService } from '../../service/courses/courses.service';
import { EnrollmentsService } from '../../service/enrollments/enrollments.service';
import { Student } from '../../models/students.model';
import { Course } from '../../models/courses.model';

@Component({
  selector: 'enroll-reg',
  templateUrl: './enroll-reg.component.html',
  styleUrls: ['./enroll-reg.component.scss']
})
export class EnrollRegComponent implements OnInit {

  students: Student[] = [];
  courses: Course[] = [];

  selectedStudentName: string | null = null;
  selectedCourseName: string | null = null;

  enrollments: any[] = []; // list of enrollments

  constructor(
    private _StudentsService: StudentsService,
    private _CoursesService: CoursesService,
    private _EnrollmentsService: EnrollmentsService
  ) {}

  ngOnInit(): void {
    this.loadStudents();
    this.loadCourses();
    this.loadEnrollments();
  }

  loadStudents() {
    this._StudentsService.getStudents().subscribe({
      next: (res) => this.students = res,
      error: (err) => console.error('Error loading students:', err)
    });
  }

  loadCourses() {
    this._CoursesService.getCourses().subscribe({
      next: (res) => this.courses = res,
      error: (err) => console.error('Error loading courses:', err)
    });
  }

  loadEnrollments() {
    this._EnrollmentsService.getAllEnrollments().subscribe({
      next: (res) => this.enrollments = res,
      error: (err) => console.error('Error loading enrollments:', err)
    });
  }

  enrollStudent() {
    if (this.selectedStudentName && this.selectedCourseName) {
      const enrollment = {
        studentName: this.selectedStudentName,
        courseName: this.selectedCourseName
      };

      this._EnrollmentsService.enrollStudent(enrollment.courseName,enrollment.studentName).subscribe({
        next: (res) => {
          console.log('Enrolled successfully:', res);
          this.loadEnrollments();
        },
        error: (err) => console.error('Error enrolling student:', err)
      });
    }
  }
}
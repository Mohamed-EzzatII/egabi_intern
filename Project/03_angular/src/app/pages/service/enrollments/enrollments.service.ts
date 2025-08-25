import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Enrollment } from '../../models/enrollments.model';

@Injectable({
  providedIn: 'root'
})
export class EnrollmentsService {

  private baseUrl = `${environment.baseUrl}/enrollments`;

  constructor(private http: HttpClient) {}

  // 1. Enroll Student in Course
  enrollStudent(courseName: string, studentName: string): Observable<any> {
    const body = { courseName, studentName };
    return this.http.post(`${this.baseUrl}/enroll/student/${body.studentName}/course/${body.courseName}`, body);
  }

  // 2. Update Student Grade
  updateGrade(courseId: number, studentId: number, grade: number): Observable<any> {
    const body = { courseId, studentId, grade };
    return this.http.put(`${this.baseUrl}/grade`, body);
  }

  // 3. Unenroll Student
  unenrollStudent(courseId: number, studentId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/unenroll/course/${courseId}/student/${studentId}`);
  }

  // 4. Get Student Enrollments (all courses of a student)
  getStudentEnrollments(studentId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/student/${studentId}`);
  }

  // 5. Get Course Roster (all students in a course)
  getCourseRoster(courseId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/course/${courseId}`);
  }

  // 6. Get Specific Grade
  getStudentGrade(courseId: number, studentId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/grade/course/${courseId}/student/${studentId}`);
  }

  // 7. Get All Enrollments (admin view)
  getAllEnrollments(): Observable<Enrollment[]> {
    return this.http.get<Enrollment[]>(`${this.baseUrl}/list`);
  }
}

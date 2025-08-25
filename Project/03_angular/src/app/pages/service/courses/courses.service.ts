import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Course } from '../../models/courses.model';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  private baseUrl = `${environment.baseUrl}/courses`;

  constructor(private http: HttpClient) { }

  // 1. Get All Courses
  getCourses(): Observable<any> {
    return this.http.get(`${this.baseUrl}/list`);
  }

  // 2. Find Course by ID
  getCourseById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/id/${id}`);
  }

  // 3. Find Courses by Name
  getCoursesByName(name: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/find/name/${name}`);
  }

  // 4. Count Courses
  getCoursesCount(): Observable<any> {
    return this.http.get(`${this.baseUrl}/count`);
  }

  // 5. Add New Course
  addNewCourse(course: Course): Observable<any> {
    return this.http.post(`${this.baseUrl}/add`, course);
  }

  // 6. Update Course Information
  updateCourse(id: number, course: Partial<Course>): Observable<any> {
    return this.http.put(`${this.baseUrl}/update/${id}`, course);
  }

  // 7. Delete Course by ID
  deleteCourseById(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/id/${id}`);
  }

  // 8. Get Courses In Faculty
  getCoursesByFaculty(facultyId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/faculty/${facultyId}`);
  }

  // 9. Get Courses with Minimum Level
  getCoursesByMinLevel(minLevel: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/level/${minLevel}`);
  }

  // 10. Delete Course by Name
  deleteCourseByName(name: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/name/${name}`);
  }
}
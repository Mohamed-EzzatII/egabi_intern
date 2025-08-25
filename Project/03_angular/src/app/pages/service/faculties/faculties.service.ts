import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Faculty } from '../../models/faculties.model';

@Injectable({
  providedIn: 'root'
})
export class FacultiesService {

  private baseUrl = `${environment.baseUrl}/faculties`;

  constructor(private http: HttpClient) { }

  // 1. Get All Faculties
  getFaculties(): Observable<any> {
    return this.http.get(`${this.baseUrl}/list`);
  }

  // 2. Get Faculty by ID
  getFacultyById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/find/id/${id}`);
  }

  // 3. Get Faculty by Name
  getFacultyByName(name: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/find/name/${name}`);
  }

  // 4. Count Faculties
  getFacultiesCount(): Observable<any> {
    return this.http.get(`${this.baseUrl}/count`);
  }

  // 5. Add New Faculty
  addNewFaculty(faculty: Faculty): Observable<any> {
    return this.http.post(`${this.baseUrl}/add`, faculty);
  }

  // 6. Update Faculty Information
  updateFaculty(id: number, faculty: Faculty): Observable<any> {
    return this.http.put(`${this.baseUrl}/update/${id}`, faculty);
  }

  // 7. Delete Faculty by ID
  deleteFacultyById(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/id/${id}`);
  }

  // 8. Delete Faculty by Name
  deleteFacultyByName(name: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/name/${name}`);
  }

  // 9. Get Faculty Courses
  getFacultyCourses(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}/courses`);
  }

  // 10. Get Faculty Students
  getFacultyStudents(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}/students`);
  }
}
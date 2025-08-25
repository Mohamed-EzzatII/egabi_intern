import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Student } from '../../models/students.model';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class StudentsService {
  constructor(private http: HttpClient) { }
  getStudents() :Observable<any> {
    return this.http.get(`${environment.baseUrl}/students/list`);
  }
  getStudentsByFaculty(facultyName: string) :Observable<any> {
    return this.http.get(`${environment.baseUrl}/students/find/faculty/${facultyName}`);
  }
  getStudentsByLevel(level: number) :Observable<any> {
    return this.http.get(`${environment.baseUrl}/students/find/level/${level}`);
  }
  getStudentByName(name: string) :Observable<any> {
    return this.http.get(`${environment.baseUrl}/students/find/name/${name}`);
  }
  getStudentById(id: number) :Observable<any> {
    return this.http.get(`${environment.baseUrl}/students/find/id/${id}`);
  }
  getStudentsNumber() :Observable<any> {
    return this.http.get(`${environment.baseUrl}/students/count`);
  }
  addNewStudent(student: Student) :Observable<any> {
    return this.http.post(`${environment.baseUrl}/students/add`, student);
  }
  updateStudent(id: number, student: Student) :Observable<any> {
    return this.http.put(`${environment.baseUrl}/students/update/${id}`, student);
  }
  deleteStudentById(id: number) :Observable<any> {
    return this.http.delete(`${environment.baseUrl}/students/delete/${id}`);
  }
  deleteStudentsByName(studentName: string) :Observable<any> {
    return this.http.delete(`${environment.baseUrl}/students/delete/name/${studentName}`);
  }
}
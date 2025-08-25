import { Course } from './../../models/courses.model';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Faculty } from '../../models/faculties.model';
import { FacultiesService } from '../../service/faculties/faculties.service';
import { StudentsService } from '../../service/students/students.service';
import { CoursesService } from '../../service/courses/courses.service';
@Component({
  selector: 'app-course-reg',
  templateUrl: './course-reg.component.html',
  styleUrls: ['./course-reg.component.scss']
})
export class CourseRegComponent implements OnInit {

  course: Course = {
    courseName:'',
    minLevel:0,
    facultyName:''
  };
  searchName:string='';
  faculties: Faculty[] = [];
  courses: Course[] = [];

  constructor(
    private http: HttpClient,
    private _FacultiesService:FacultiesService,
    private _StudentsService:StudentsService,
    private _CoursesService:CoursesService
  ) {}

  ngOnInit(): void {
    this.loadFaculties();
    this.loadCourses();
  }

  loadFaculties() {
    this._FacultiesService.getFaculties().subscribe({
      next: (res) => {
        this.faculties = res;
      },
      error: (err) => console.error('Error loading faculties:', err)
    });
  }

  addCourse() { 
    this._CoursesService.addNewCourse(this.course).subscribe({
      next: (res) => {
        console.log('Course added successfully:', res);
        this.loadCourses(); // refresh list
      },
      error: (err) => console.error('Error:', err)
    });
  }

  loadCourses() {
    this._CoursesService.getCourses().subscribe({
      next: (res) => {
        this.courses = res;
        console.log(this.courses);
      },
      error: (err) => console.error('Error loading courses:', err)
    });
  }
  searchCourse() {
    this.courses=[]
    if (!this.searchName) {
      this.course.courseName = '';
      this.course.facultyName='';
      this.course.minLevel=-1;
      return;
    }
    this._CoursesService.getCoursesByName(this.searchName).subscribe({
      next: (res) => {
        this.courses = res;
        console.log('Search results:', res);
      },
      error: (err) => console.error('Error searching courses:', err)
    });
  }
}

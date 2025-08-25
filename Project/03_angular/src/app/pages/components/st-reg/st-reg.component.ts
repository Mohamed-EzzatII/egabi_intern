import { Component, OnInit } from '@angular/core';
import { Student } from '../../models/students.model';
import { StudentsService } from '../../service/students/students.service';

@Component({
  selector: 'app-st-reg',
  templateUrl: './st-reg.component.html',
  styleUrls: ['./st-reg.component.css']
})
export class StRegComponent implements OnInit {

  student: Student = {
    studentName: '',
    studentLevel: 0,
    facultyName: ''
  };

  searchName: string = '';
  students: Student[] = [];

  constructor(
    private _StudentsService: StudentsService
  ) {}

  ngOnInit(): void {
    this.loadStudents();
  }

  addStudent() {
    this._StudentsService.addNewStudent(this.student).subscribe({
      next: (res) => {
        console.log('Student added successfully:', res);
        this.loadStudents(); // refresh list
      },
      error: (err) => console.error('Error:', err)
    });
  }

  loadStudents() {
    this._StudentsService.getStudents().subscribe({
      next: (res) => {
        this.students = res;
        console.log(this.students);
      },
      error: (err) => console.error('Error loading students:', err)
    });
  }

  searchStudent() {
    this.students = [];
    if (!this.searchName) {
      this.student.studentName = '';
      this.student.facultyName = '';
      this.student.studentLevel = -1;
      return;
    }
    this._StudentsService.getStudentByName(this.searchName).subscribe({
      next: (res) => {
        this.students = res;
        console.log('Search results:', res);
      },
      error: (err) => console.error('Error searching students:', err)
    });
  }
}

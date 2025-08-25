import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Faculty } from '../../models/faculties.model';
import { FacultiesService } from '../../service/faculties/faculties.service';

@Component({
  selector: 'app-faculty-reg',
  templateUrl: './faculty-reg.component.html',
  styleUrls: ['./faculty-reg.component.scss']
})
export class FacultyRegComponent implements OnInit {

  faculty: Faculty = {
    facultyName: '',
    specialization: ''
  };

  searchName: string = '';
  faculties: Faculty[] = [];

  constructor(
    private http: HttpClient,
    private _FacultiesService: FacultiesService
  ) {}

  ngOnInit(): void {
    this.loadFaculties();
  }

  loadFaculties() {
    this._FacultiesService.getFaculties().subscribe({
      next: (res) => {
        this.faculties = res;
      },
      error: (err) => console.error('Error loading faculties:', err)
    });
  }

  addFaculty() { 
    this._FacultiesService.addNewFaculty(this.faculty).subscribe({
      next: (res) => {
        console.log('Faculty added successfully:', res);
        this.loadFaculties(); // refresh list
      },
      error: (err) => console.error('Error:', err)
    });
  }

  searchFaculty() {
    this.faculties = [];
    if (!this.searchName) {
      this.faculty.facultyName = '';
      this.faculty.specialization = '';
      return;
    }
    this._FacultiesService.getFacultyByName(this.searchName).subscribe({
      next: (res) => {
        this.faculties = res;
        console.log('Search results:', res);
      },
      error: (err) => console.error('Error searching faculties:', err)
    });
  }
}

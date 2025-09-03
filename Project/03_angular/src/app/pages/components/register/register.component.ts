import { Component } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent {
  studentName = '';
  studentLevel: number = 1;
  username = '';
  password = '';
  facultyName = '';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onRegister() {
    const userData = {
      studentName: this.studentName,
      studentLevel: this.studentLevel,
      username: this.username,
      password: this.password,
      facultyName: this.facultyName
    };
    this.authService.register(userData).subscribe({
      next: () => this.router.navigate(['/login']), // Redirect to login after registration
      error: (err) => this.errorMessage = err.message
    });
  }
}
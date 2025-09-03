import { Component } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  username = '';
  password = '';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    this.errorMessage = ''; // Reset error message
    this.authService.login({ username: this.username, password: this.password }).subscribe({
      next: () => this.router.navigate(['/st-reg']), // Redirect to student registration on success
      error: (err) => this.errorMessage = err.message || 'Login failed. Please check your credentials.'
    });
  }
}
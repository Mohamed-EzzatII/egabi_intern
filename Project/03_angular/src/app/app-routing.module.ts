import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StRegComponent } from '../app/pages/components/st-reg/st-reg.component';
import { CourseRegComponent } from '../app/pages/components/course-reg/course-reg.component';
import { FacultyRegComponent } from './pages/components/faculty-reg/faculty-reg.component';
import { EnrollRegComponent } from '../app/pages/components/enroll-reg/enroll-reg.component';
import { LoginComponent } from '../app/pages/components/login/login.component';
import { RegisterComponent } from '../app/pages/components/register/register.component';
import {NavBarComponent} from '../app/pages/components/nav-bar/nav-bar.component'
import { AuthGuard } from './guards/auth.guard';
const routes: Routes = [
  { path: 'st-reg', component: StRegComponent, canActivate: [AuthGuard] },
  { path: 'course-reg', component: CourseRegComponent, canActivate: [AuthGuard] },
  { path: 'faculty-reg', component: FacultyRegComponent, canActivate: [AuthGuard] },
  { path: 'enroll-reg', component: EnrollRegComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent }, // Public
  { path: 'register', component: RegisterComponent }, // Public
  { path: '', redirectTo: '/st-reg', pathMatch: 'full' } // default
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

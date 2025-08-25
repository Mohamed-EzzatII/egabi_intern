import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StRegComponent } from '../app/pages/components/st-reg/st-reg.component';
import { CourseRegComponent } from '../app/pages/components/course-reg/course-reg.component';
import { FacultyRegComponent } from './pages/components/faculty-reg/faculty-reg.component';
import { EnrollRegComponent } from '../app/pages/components/enroll-reg/enroll-reg.component';
import {NavBarComponent} from '../app/pages/components/nav-bar/nav-bar.component'
const routes: Routes = [
  { path: 'st-reg', component: StRegComponent },
  { path: 'course-reg', component: CourseRegComponent },
  { path: 'faculty-reg', component: FacultyRegComponent },
  { path: 'enroll-reg', component: EnrollRegComponent },
  { path: '', redirectTo: '/st-reg', pathMatch: 'full' } // default
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

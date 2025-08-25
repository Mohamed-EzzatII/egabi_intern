// enrollment.model.ts
import { Course } from "./courses.model";
import { Student } from "./students.model";

export interface Enrollment {
  course?: Course;       // relation to course
  student?: Student;     // relation to student
  grade?: number;
}

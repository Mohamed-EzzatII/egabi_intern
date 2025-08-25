// course.model.ts
import { Faculty } from "./faculties.model";

export interface Course {
  courseName: string;
  minLevel: number;
  facultyName: string;
}

---------------------------------------------------
-- Drop Tables
---------------------------------------------------

DROP TABLE Enrollment;
DROP TABLE Course;
DROP TABLE Student;
DROP TABLE Faculty;

---------------------------------------------------
-- Creating Tables
---------------------------------------------------

CREATE TABLE Faculty(
	faculty_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	faculty_name VARCHAR(100) NOT NULL,
	specialization VARCHAR(50) NOT NULL
);

CREATE TABLE Student(
	student_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	student_name VARCHAR(100) NOT NULL,
	student_level INT NOT NULL CHECK (student_level >= 0),
	faculty_id INT REFERENCES Faculty(faculty_id)
);

CREATE TABLE Course(
	course_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	course_name VARCHAR(100) NOT NULL,
	min_level INT NOT NULL CHECK (min_level >= 0),
	faculty_id INT REFERENCES Faculty(faculty_id)
);

CREATE TABLE Enrollment(
	course_id INT REFERENCES Course(course_id),
	student_id INT REFERENCES Student(student_id),
	grade NUMERIC(5,2) CHECK (grade >= 0)
);

---------------------------------------------------
-- Inserting random records
---------------------------------------------------

INSERT INTO Faculty (faculty_name, specialization) VALUES
('Faculty of Engineering', 'Electrical Engineering'),
('Faculty of Medicine', 'Human Medicine'),
('Faculty of Computers and Information', 'Computer Science'),
('Faculty of Commerce', 'Business Administration');

INSERT INTO Student (student_name, student_level, faculty_id) VALUES
('Ahmed Mohamed', 2, 1),
('Sarah Ali', 3, 2),
('Mohamed Hassan', 1, 3),
('Fatma Mahmoud', 4, 1),
('Youssef Ibrahim', 2, 4);

INSERT INTO Course (course_name, min_level, faculty_id) VALUES
('Electrical Circuits', 2, 1),
('Medical Devices', 3, 1),
('Anatomy', 2, 2),
('Programming 1', 1, 3),
('Project Management', 2, 4);

INSERT INTO Enrollment (course_id, student_id, grade) VALUES
(1, 1, 85.5),
(2, 1, 90.0),
(3, 2, 88.0),
(4, 3, 95.0),
(5, 5, 78.5);

---------------------------------------------------
-- Display all records
---------------------------------------------------

SELECT * FROM Student;
SELECT * FROM Faculty;
SELECT * FROM Course;
SELECT * FROM Enrollment;

---------------------------------------------------
-- CRUD Operations
---------------------------------------------------


-- read
SELECT student_name AS "Student Name",student_level AS "Student Level"
FROM Student
WHERE student_level BETWEEN 1 AND 3;

-- update
UPDATE Student
SET student_name = 'Mohamed Ezzat',
	student_level = 5,
	faculty_id = 1
WHERE student_id = 1;

-- failed update due to constriant
UPDATE Student
SET student_level = -1
WHERE student_id = 1;

-- delete
DELETE FROM Student
WHERE student_id = 4
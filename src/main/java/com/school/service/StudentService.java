package com.school.service;

import java.util.List;

import com.school.entity.Student;

public interface StudentService {
	Student addStudent(Student student);

	Student getStudentById(int id);

	Student getStudentByName(String name);

	List<Student> getAllStudents();

	Student updateStudentContact(int id, String contactNum);

	Student updateStudentLocation(int id, String location);

	void deleteStudent(int id);
}

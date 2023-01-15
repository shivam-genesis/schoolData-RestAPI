package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.entity.Student;
import com.school.exceptions.ApiResponse;
import com.school.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;

	@PostMapping("/student")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		Student s = this.studentService.addStudent(student);
		return new ResponseEntity<Student>(s, HttpStatus.CREATED);
	}

	@GetMapping("/student/id/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") int id) {
		Student s = this.studentService.getStudentById(id);
		return new ResponseEntity<Student>(s, HttpStatus.OK);
	}

	@GetMapping("/student/name/{name}")
	public ResponseEntity<Student> getStudentByName(@PathVariable("name") String name) {
		Student s = this.studentService.getStudentByName(name);
		return new ResponseEntity<Student>(s, HttpStatus.OK);
	}

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getStudentById() {
		List<Student> s = this.studentService.getAllStudents();
		return new ResponseEntity<List<Student>>(s, HttpStatus.OK);
	}

	@PutMapping("/student/{id}/contact/{contactNum}")
	public ResponseEntity<Student> updateStudentContact(@PathVariable("id") int id,
			@PathVariable("contactNum") String contactNum) {
		Student s = this.studentService.updateStudentContact(id, contactNum);
		return new ResponseEntity<Student>(s, HttpStatus.OK);
	}

	@PutMapping("/student/{id}/location/{location}")
	public ResponseEntity<Student> updateStudentLocation(@PathVariable("id") int id,
			@PathVariable("location") String location) {
		Student s = this.studentService.updateStudentLocation(id, location);
		return new ResponseEntity<Student>(s, HttpStatus.OK);
	}

	@DeleteMapping("/student/{id}")
	public ResponseEntity<ApiResponse> deleteStudent(@PathVariable("id") int id) {
		this.studentService.deleteStudent(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Student Deleted!!", true), HttpStatus.OK);
	}
}

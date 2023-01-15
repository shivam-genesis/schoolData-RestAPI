package com.school.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.entity.Student;
import com.school.exceptions.AlreadyExistException;
import com.school.exceptions.ResourceNotFoundException;
import com.school.exceptions.SaveException;
import com.school.exceptions.ValidationExceptionEdit;
import com.school.repository.StudentRepository;
import com.school.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student addStudent(Student student) {
		Optional<Student> existedStudent = this.studentRepository.findById(student.getId());
		if (student.getId() == 0 || !existedStudent.isEmpty()) {
			throw new AlreadyExistException("Student",String.valueOf(student.getId()));
		} else if (student.getFirstName().length() < 3) {
			throw new ValidationExceptionEdit("FirstName","2");
		} else if (student.getLastName().length() < 3) {
			throw new ValidationExceptionEdit("LastName","2");
		} else if (student.getContactNum().length() < 4) {
			throw new ValidationExceptionEdit("ContactNumber","3");
		} else if (student.getLocation().length() < 4) {
			throw new ValidationExceptionEdit("Location","3");
		}
		Student savedStudent = this.studentRepository.save(student);
		if (savedStudent == null) {
			throw new SaveException("Student");
		}
		return savedStudent;
	}

	@Override
	public Student getStudentById(int id) {
		Student s = this.studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "id", String.valueOf(id)));
		return s;
	}

	@Override
	public Student getStudentByName(String name) {
		Student s = this.studentRepository.findByFirstName(name);
		if(s==null) {
			throw new ResourceNotFoundException("Student","name",name);
		}
		return s;
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> students = this.studentRepository.findAll();
		if(students.isEmpty()) {
			throw new ResourceNotFoundException("Student"); 
		}
		return students;
	}

	@Override
	public Student updateStudentContact(int id, String contactNum) {
		if (contactNum.length() < 4) {
			throw new ValidationExceptionEdit("ContactNumber","3");
		}
		Student s = this.studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "id", String.valueOf(id)));	
		s.setContactNum(contactNum);
		Student updatedStudent = this.studentRepository.save(s);
		return updatedStudent;
	}

	@Override
	public Student updateStudentLocation(int id, String location) {
		if (location.length() < 4) {
			throw new ValidationExceptionEdit("Location","3");
		}
		Student s = this.studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "id", String.valueOf(id)));	
		s.setLocation(location);
		Student updatedStudent = this.studentRepository.save(s);
		return updatedStudent;
	}

	@Override
	public void deleteStudent(int id) {
		this.studentRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Student", "id", String.valueOf(id)));
		this.studentRepository.deleteById(id);
	}

}

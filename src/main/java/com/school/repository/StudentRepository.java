package com.school.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, Integer> {
	public Student findByFirstName(String name);
}

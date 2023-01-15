package com.school.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="students")
public class Student {
	private int id;
	private String firstName;
	private String lastName;
	private String contactNum;
	private String location;
	private Branch branch;
}

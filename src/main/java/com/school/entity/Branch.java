package com.school.entity;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "branches")
public class Branch {
	private String branchCode;
	private String branchName;
	private String branchHeadName;

	private List<Student> students;
}

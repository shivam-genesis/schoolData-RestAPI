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

import com.school.entity.Branch;
import com.school.exceptions.ApiResponse;
import com.school.service.BranchService;

@RestController
public class BranchController {
	@Autowired
	private BranchService branchService;

	@PostMapping("/branch")
	public ResponseEntity<Branch> addStudent(@RequestBody Branch branch) {
		Branch b = this.branchService.addBranch(branch);
		return new ResponseEntity<Branch>(b, HttpStatus.CREATED);
	}

	@GetMapping("/branch/{code}")
	public ResponseEntity<Branch> getBranchByCOde(@PathVariable("code") String code) {
		Branch b = this.branchService.getBranchByCode(code);
		return new ResponseEntity<Branch>(b, HttpStatus.OK);
	}

	@GetMapping("/branches")
	public ResponseEntity<List<Branch>> getAllBranch() {
		List<Branch> b = this.branchService.getAllBranches();
		return new ResponseEntity<List<Branch>>(b, HttpStatus.OK);
	}

	@PutMapping("/branch/{code}/name/{name}")
	public ResponseEntity<Branch> updateBranchHeadName(@PathVariable("code") String code,
			@PathVariable("name") String name) {
		Branch b = this.branchService.updateBranchHeadName(code, name);
		return new ResponseEntity<Branch>(b, HttpStatus.OK);
	}

	@DeleteMapping("/branch/{code}")
	public ResponseEntity<ApiResponse> deleteStudent(@PathVariable("code") String code) {
		this.branchService.deleteBranch(code);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Student Deleted!!", true), HttpStatus.OK);
	}
}

package com.school.service;

import java.util.List;

import com.school.entity.Branch;

public interface BranchService {
	Branch addBranch(Branch branch);

	Branch getBranchByCode(String code);

	List<Branch> getAllBranches();

	Branch updateBranchHeadName(String code, String name);

	void deleteBranch(String code);

}

package com.school.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.entity.Branch;
import com.school.exceptions.AlreadyExistException;
import com.school.exceptions.ResourceNotFoundException;
import com.school.exceptions.SaveException;
import com.school.exceptions.ValidationExceptionEdit;
import com.school.repository.BranchRepository;
import com.school.service.BranchService;

import jakarta.validation.ValidationException;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchRepository branchRepository;

	@Override
	public Branch addBranch(Branch branch) {
		String code = branch.getBranchCode();
		Optional<Branch> alreadyExist = this.branchRepository.findById(branch.getBranchCode());
		if (!alreadyExist.isEmpty()) {
			throw new AlreadyExistException("Branch", branch.getBranchCode());
		} else if (code.charAt(0) < 65 && code.charAt(0) > 90) {
			throw new ValidationException(code);
		} else if (branch.getBranchName().length() < 2) {
			throw new ValidationExceptionEdit("Branch Name", "1");
		} else if (branch.getBranchHeadName().length() < 3) {
			throw new ValidationExceptionEdit("Branch Head Name", "2");
		}
		Branch b = this.branchRepository.save(branch);
		if (b == null) {
			throw new SaveException("Branch");
		}
		return b;
	}

	@Override
	public Branch getBranchByCode(String code) {
		Branch b = this.branchRepository.findById(code)
				.orElseThrow(() -> new ResourceNotFoundException("Branch", "BranchID", code));
		return b;
	}

	@Override
	public List<Branch> getAllBranches() {
		List<Branch> b = this.branchRepository.findAll();
		if (b.isEmpty()) {
			throw new ResourceNotFoundException("Branch");
		}
		return b;
	}

	@Override
	public Branch updateBranchHeadName(String code, String name) {
		Branch b = this.branchRepository.findById(code)
				.orElseThrow(() -> new ResourceNotFoundException("Branch", "BranchID", code));
		b.setBranchHeadName(name);
		Branch updatedBranch = this.branchRepository.save(b);
		return updatedBranch;
	}

	@Override
	public void deleteBranch(String code) {
		this.branchRepository.findById(code)
				.orElseThrow(() -> new ResourceNotFoundException("Branch", "BranchID", code));
		this.branchRepository.deleteById(code);
	}

}

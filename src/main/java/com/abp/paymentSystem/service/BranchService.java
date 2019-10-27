package com.abp.paymentSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abp.paymentSystem.entity.Branch;
import com.abp.paymentSystem.entity.Faculty;
import com.abp.paymentSystem.repository.BranchRepository;

@Service
public class BranchService {

	@Autowired BranchRepository branchrepo;
	
	
	/*
	 * public Faculty findFacultyID(long id) { return
	 * branchrepo.findFacultyByBranch_Id(id); }
	 */
	public List<Branch> listAllBranches() {
        return (List<Branch>) branchrepo.findAll();
    }
	
	public void saveBranch(Branch branch) {
		branchrepo.save(branch);
    }
     
    public Branch getBranch(long id) {
        return branchrepo.findById(id).get();
    }
     
    public void deleteBranch(long id) {
    	branchrepo.deleteById(id);
}
}


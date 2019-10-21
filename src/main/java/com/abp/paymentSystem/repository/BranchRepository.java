package com.abp.paymentSystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abp.paymentSystem.entity.Branch;

@Repository
public interface BranchRepository extends CrudRepository<Branch, Long> {
	
	
}

package com.abp.paymentSystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abp.paymentSystem.entity.Branch;

@Repository
public interface BranchRepository extends CrudRepository<Branch, Long> {
	 public List<Branch> findByFacultyId(long id);
}

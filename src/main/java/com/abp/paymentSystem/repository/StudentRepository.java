package com.abp.paymentSystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.abp.paymentSystem.entity.Student;

public interface StudentRepository extends CrudRepository<Student,Long>{
	 public List<Student> findByBranchId(long id);

}

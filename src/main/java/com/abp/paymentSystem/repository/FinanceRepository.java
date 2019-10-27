package com.abp.paymentSystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abp.paymentSystem.entity.Course;
import com.abp.paymentSystem.entity.Finance;

@Repository
public interface FinanceRepository extends CrudRepository<Finance, Long> {
	 public List<Finance> findByFacultyId(long id);
}

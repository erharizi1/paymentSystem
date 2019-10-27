package com.abp.paymentSystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abp.paymentSystem.entity.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long>{
 public List<Course> findByBranchId(long id);

}

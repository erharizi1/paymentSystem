package com.abp.paymentSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abp.paymentSystem.entity.Course;
import com.abp.paymentSystem.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired CourseRepository courserepo;
	
	public List<Course> listAllCourses() {
        return (List<Course>) courserepo.findAll();
    }
	
	public List<Course> listByBranch(long id){
		return (List<Course>) courserepo.findByBranchId(id);
	}
	public void saveCourse(Course course) {
		courserepo.save(course);
    }
     
    public Course getCourse(long id) {
        return courserepo.findById(id).get();
    }
     
    public void deleteCourse(long id) {
    	courserepo.deleteById(id);
}
}

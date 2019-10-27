package com.abp.paymentSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abp.paymentSystem.entity.Student;
import com.abp.paymentSystem.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {
	   @Autowired
	    private StudentRepository repo;
	    public List<Student> listAll() {
	        return (List<Student>) repo.findAll();
	    }
	    
	    public List<Student> listByBranch(long id){
			return (List<Student>) repo.findByBranchId(id);
		}
	    
	    public void save(Student student) {
	        repo.save(student);
	    }
	    
	     
	    public Student get(long id) {
	        return repo.findById(id).get();
	    }
	     
	    public void delete(long id) {
	        repo.deleteById(id);
	    }
}

package com.abp.paymentSystem.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abp.paymentSystem.entity.Faculty;
import com.abp.paymentSystem.repository.FacultyRepository;

import java.util.List;

@Service
public class FacultyService {
	   @Autowired
	    private FacultyRepository repo;
	    public List<Faculty> listAll() {
	        return (List<Faculty>) repo.findAll();
	    }
	     
	    public void save(Faculty faculty) {
	        repo.save(faculty);
	    }
	    
	     
	    public Faculty get(long id) {
	        return repo.findById(id).get();
	    }
	     
	    public void delete(long id) {
	        repo.deleteById(id);
	    }
}

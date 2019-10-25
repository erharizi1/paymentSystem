package com.abp.paymentSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abp.paymentSystem.entity.Student;
import com.abp.paymentSystem.entity.User;
import com.abp.paymentSystem.repository.ForUserRepository;

@Service
public class UserService {
	@Autowired
	private ForUserRepository repo;
	
	public void saveUser(User user) {
		repo.save(user);
    }
	
	public User getUser(String email) {
		return repo.findByEmail(email);
		
	}
	
	 public void delete(int id) {
	        repo.deleteById(id);
	    }
	

	

}

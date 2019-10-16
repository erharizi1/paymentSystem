package com.abp.paymentSystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.abp.paymentSystem.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
	
	User findById(int id);
}

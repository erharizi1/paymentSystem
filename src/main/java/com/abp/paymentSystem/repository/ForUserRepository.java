package com.abp.paymentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abp.paymentSystem.entity.User;

@Repository
public interface ForUserRepository extends JpaRepository<User,Integer>{
	User findByEmail(String email);
	

}

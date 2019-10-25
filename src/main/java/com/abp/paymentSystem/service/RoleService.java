package com.abp.paymentSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abp.paymentSystem.entity.Role;
import com.abp.paymentSystem.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository repo;
	
	 public Role getRole(int id) {
        return repo.findById(id).get();
    }

}

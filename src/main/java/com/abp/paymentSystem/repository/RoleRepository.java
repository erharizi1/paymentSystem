package com.abp.paymentSystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.abp.paymentSystem.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

	Role findByRole(String role); 
}

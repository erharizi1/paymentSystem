package com.abp.paymentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abp.paymentSystem.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{
	

}

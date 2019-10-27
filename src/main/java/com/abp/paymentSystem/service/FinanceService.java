package com.abp.paymentSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abp.paymentSystem.entity.Finance;
import com.abp.paymentSystem.repository.FinanceRepository;

@Service
public class FinanceService {
	@Autowired
	private FinanceRepository repo;
	
	public List<Finance> listAllFinances(){
		return  (List<Finance>) repo.findAll();
	}
	
	public List<Finance> listByFaculty(long id){
		return (List<Finance>) repo.findByFacultyId(id);
	}
	
	public void saveFinance(Finance f) {
		repo.save(f);
	}
	public Finance getFinance(long id) {
		return repo.findById(id).get();
	}
	public void deleteFinance(long id) {
		repo.deleteById(id);
	}

}

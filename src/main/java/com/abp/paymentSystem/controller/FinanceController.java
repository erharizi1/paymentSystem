package com.abp.paymentSystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abp.paymentSystem.entity.Faculty;
import com.abp.paymentSystem.entity.Finance;
import com.abp.paymentSystem.service.FacultyService;
import com.abp.paymentSystem.service.FinanceService;

@Controller
public class FinanceController {
	@Autowired
	private FinanceService financeService;
	@Autowired
	private FacultyService facultyService;
	@RequestMapping("/Finance/viewFinance")
	public String indexOfFinance(Model model) {
		List<Finance> allFinances=(List<Finance>)financeService.listAllFinances();
		model.addAttribute("allFinances",allFinances);
		return "Finance/viewFinance";
	}
	
	@RequestMapping("/Finance/newFinance")
	public String createNewFinance(Model model) {
		Finance finance=new Finance();
		model.addAttribute("finance",finance);
		model.addAttribute("allFaculties",facultyService.listAll());
		return "Finance/newFinance";
	}
	@RequestMapping(value = "/saveFinance", method = RequestMethod.POST)
	public String saveFinances(@Valid @ModelAttribute("finance") Finance finance,Model model) {
		financeService.saveFinance(finance);
		Faculty faculty=facultyService.get(finance.getFaculty().getId());
		faculty.getFinaces().add(finance);
		facultyService.save(faculty);
		
	    return "redirect:/Finance/viewFinance";
	}

}

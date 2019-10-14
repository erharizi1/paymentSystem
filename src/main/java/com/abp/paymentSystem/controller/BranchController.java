package com.abp.paymentSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abp.paymentSystem.entity.Branch;
import com.abp.paymentSystem.service.BranchService;
import com.abp.paymentSystem.service.FacultyService;

@Controller
public class BranchController {
	
	@Autowired private BranchService branchService;
	@Autowired private FacultyService facultyService;
	
	@RequestMapping("/")
	public String showform() {
		return "main-menu";
	}
	

	@RequestMapping("/Branch/list-branches")
	public String viewBranches(Model model) {
	    List<Branch> listBranches = (List<Branch>) branchService.listAllBranches();
	    model.addAttribute("listBranches", listBranches);
	        return "Branch/list-branches";
	}
	
	@RequestMapping("/Branch/register-form")
	public String showBranches(Model model) {
		Branch branch = new Branch();
		    model.addAttribute("branch", branch);
		    model.addAttribute("allFaculties", facultyService.listAll());
		return "Branch/register-form";
	}

	@RequestMapping(value = "/savebranch", method = RequestMethod.POST)
	public String saveBranches(@ModelAttribute("branch") Branch branch) {
		branchService.saveBranch(branch);
		return "redirect:/Branch/list-branches";
	}
	
	@RequestMapping("/deletebranch/{id}")
	public String deleteBranch(@PathVariable(name = "id") long id) {
	    branchService.deleteBranch(id);
	    return "redirect:/";       
	}

}

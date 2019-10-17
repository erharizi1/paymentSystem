package com.abp.paymentSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.abp.paymentSystem.entity.Faculty;
import com.abp.paymentSystem.service.FacultyService;

@Controller
public class AdminController {
	@Autowired
	private FacultyService facultyService;
	
	@RequestMapping("/admin-form")
	public String showadmin() {
		return "admin-form";
	}

	@RequestMapping("/admin-form/addFaculty")
	    public ModelAndView redirectWithUsingForwardPrefix(@ModelAttribute("faculty") Faculty faculty, ModelMap model) {
		facultyService.save(faculty);
	        return new ModelAndView("forward:/admin-form", model);
	    }
	
	@GetMapping("/admin-form/addFacultyForm")
    public ModelAndView redirectWithPrefixToFaculty(Model model) {
		ModelAndView modelAndview = new ModelAndView("admin-form");
		model.addAttribute("faculty", new Faculty());
        model.addAttribute("showFacultyForm", 1);
        return modelAndview;
	}
	
	
	@GetMapping("/admin-form/addBranch")
    public ModelAndView redirectWithPrefix(ModelMap model) {
        model.addAttribute("adminFunction2", 2);
        return new ModelAndView("forward:/admin-form", model);
    }

}

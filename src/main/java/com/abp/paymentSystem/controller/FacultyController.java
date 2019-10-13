package com.abp.paymentSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abp.paymentSystem.entity.Faculty;
import com.abp.paymentSystem.entity.Finance;
import com.abp.paymentSystem.service.FacultyService;

@Controller
public class FacultyController {
	@Autowired
	private FacultyService facultyService;
	@RequestMapping("/viewFaculty")
	public String indexOfFaculty(Model model) {
		List<Faculty> allFaculties=(List<Faculty>)facultyService.listAll();
		model.addAttribute("allFaculties",allFaculties);
		return "viewFaculty";
	}
	@RequestMapping("/newFaculty")
	public String createNewFaculty(Model model) {		
		model.addAttribute("faculty",new Faculty());		
		return "newFaculty";
	}
	@RequestMapping(value = "/saveFaculty", method = RequestMethod.POST)
	public String saveFaculty(@ModelAttribute("faculty") Faculty faculty,Model model) {
		facultyService.save(faculty);
		return"viewFaculty";
	}
}

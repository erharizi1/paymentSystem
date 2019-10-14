package com.abp.paymentSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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



	
	
	@RequestMapping("/list_faculties")
	public String viewFaculties(Model model) {
	    List<Faculty> listFaculties = (List<Faculty>) facultyService.listAll();
	    model.addAttribute("listFaculties", listFaculties);
	        return "list_faculties";
	}
	
	@RequestMapping("/register_faculty")
	public String newFaculty(Model model) {
		Faculty faculty = new Faculty();
		    model.addAttribute("faculty", faculty);
		return "register_faculty";
	}
	
	
	
	@RequestMapping(value = "/savefaculty", method = RequestMethod.POST)
	public String saveFaculty(@ModelAttribute("faculty") Faculty faculty) {
		facultyService.save(faculty);
	    return "redirect:/";
	}
	
	@RequestMapping("/deletefaculty/{id}")
	public String deleteFaculty(@PathVariable(name = "id") long id) {
		facultyService.delete(id);
	    return "redirect:/";       
	}

	@RequestMapping(value="/detailsFaculty/{id}")
	public String showDetailsFaculty(@PathVariable(name = "id") long id,Model model) {
		model.addAttribute("faculty",facultyService.get(id));
		List<Finance> financeList=facultyService.get(id).getFinaces();
		model.addAttribute("list",financeList);
		return "detailsFaculty";

	}
}

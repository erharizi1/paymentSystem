package com.abp.paymentSystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.abp.paymentSystem.entity.Branch;
import com.abp.paymentSystem.entity.Faculty;
import com.abp.paymentSystem.entity.Finance;
import com.abp.paymentSystem.entity.Student;
import com.abp.paymentSystem.entity.Course;
import com.abp.paymentSystem.service.BranchService;
import com.abp.paymentSystem.service.CourseService;
import com.abp.paymentSystem.service.FacultyService;
import com.abp.paymentSystem.service.FinanceService;
import com.abp.paymentSystem.service.StudentService;

@Controller
public class AdminController {
	
	@Autowired
	private FacultyService facultyService;
	@Autowired 
	BranchService branchService;
	@Autowired
	CourseService courseService;
	@Autowired
	StudentService studentService;
	@Autowired
	FinanceService financeService;
	
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
	
	@GetMapping("/admin-form/addFacultyList")
	public ModelAndView indexOfFaculty(Model model) {
		ModelAndView modelAndview = new ModelAndView("admin-form");
		List<Faculty> allFaculties=(List<Faculty>)facultyService.listAll();
		model.addAttribute("allFaculties",allFaculties);
		 model.addAttribute("showFacultyList", 1);
		return modelAndview;
	}
	
	
	

	
//	@RequestMapping("/deletefaculty/{id}")
//	public  ModelAndView deleteFaculty(@PathVariable(name = "id") long id) {
//		ModelAndView modelAndview = new ModelAndView("admin-form");
//		facultyService.delete(id);
//	    return modelAndview;       
//	}
	
	@GetMapping("/admin-form/addBranchForm")
    public ModelAndView redirectBranchForm(Model model) {
		ModelAndView modelAndview = new ModelAndView("admin-form");
		model.addAttribute("branch", new Branch());
		model.addAttribute("allFaculties", facultyService.listAll());
        model.addAttribute("showBranchForm", 1);
        return modelAndview;
	}
	
	
	@RequestMapping("/admin-form/addBranch")
	public ModelAndView redirectSaveBranches(@ModelAttribute("branch") Branch branch,ModelMap model ) {
		branchService.saveBranch(branch);
		return new ModelAndView("forward:/admin-form", model);
	}

	
	@GetMapping("/admin-form/addBranchList")
	public ModelAndView redirectListBranches(Model model) {
		ModelAndView modelAndview = new ModelAndView("admin-form");
	    List<Branch> listBranches = (List<Branch>) branchService.listAllBranches();
	    model.addAttribute("listBranches", listBranches);
	    model.addAttribute("showBranchList", 1);
	        return modelAndview;
	}
	
	@GetMapping("/admin-form/addCourseForm")
	  public ModelAndView redirectCourseForm(Model model) {
			ModelAndView modelAndview = new ModelAndView("acc-form");
			model.addAttribute("course", new Course());
			model.addAttribute("allBranches", branchService.listAllBranches());
	      model.addAttribute("showCourseForm", 1);
	      return modelAndview;
		}
	
	@RequestMapping("/admin-form/addCourse")
	public ModelAndView redirectSaveCourses(@ModelAttribute("course") Course course,ModelMap model ) {
		courseService.saveCourse(course);
		return new ModelAndView("forward:/admin-form", model);
	}
	
	@GetMapping("/admin-form/addCourseList")
	public ModelAndView redirectListCourses(Model model) {
		ModelAndView modelAndview = new ModelAndView("admin-form");
	    List<Course> listCourses = (List<Course>) courseService.listAllCourses();
	    model.addAttribute("listCourses", listCourses);
	    model.addAttribute("showCourseList", 1);
	        return modelAndview;
	}
	
	@GetMapping("/admin-form/addFinanceForm")
	 public ModelAndView redirectFinanceForm(Model model) {
		ModelAndView modelAndview = new ModelAndView("admin-form");
		Finance finance=new Finance();
		model.addAttribute("finance",finance);
		model.addAttribute("allFaculties",facultyService.listAll());
		 model.addAttribute("showFinanceForm", 1);
		return modelAndview;
	}
	
	
	@RequestMapping("/admin-form/addFinance")
	public ModelAndView redirectSaveFinance(@ModelAttribute("finance") Finance finance,ModelMap model ) {
		financeService.saveFinance(finance);
		Faculty faculty=facultyService.get(finance.getFaculty().getId());
		faculty.getFinaces().add(finance);
		facultyService.save(faculty);
		return new ModelAndView("forward:/admin-form", model);
	}
		
	
	@GetMapping("/admin-form/addFinanceList")
	public ModelAndView indexOfFinance (Model model) {
		ModelAndView modelAndview = new ModelAndView("admin-form");
		List<Finance> allFinances=(List<Finance>)financeService.listAllFinances();
		model.addAttribute("allFinances",allFinances);
		 model.addAttribute("showFinancesList", 1);
		 return modelAndview;
	}
	
	@GetMapping("/admin-form/addStudentList")
	public ModelAndView indexOfStudents(Model model) {
		ModelAndView modelAndview = new ModelAndView("admin-form");
	    List<Student> listStudents = studentService.listAll();
	    model.addAttribute("listStudents", listStudents);
	    model.addAttribute("showStudentList", 1);
	        return modelAndview;
	} 
}

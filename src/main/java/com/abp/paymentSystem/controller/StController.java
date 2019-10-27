package com.abp.paymentSystem.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.abp.paymentSystem.entity.Branch;
import com.abp.paymentSystem.entity.Course;
import com.abp.paymentSystem.entity.Student;
import com.abp.paymentSystem.service.BranchService;
import com.abp.paymentSystem.service.CourseService;
import com.abp.paymentSystem.service.StudentService;


@PreAuthorize("hasAnyRole('STUDENT')")
@Controller
public class StController {
	@Autowired
	CourseService courseService;
	@Autowired
	StudentService studentService;
	@Autowired
	BranchService branchService;
	
	@RequestMapping("/student-form")
	public String showStudent() {
		return "student-form";
	}

	
	@GetMapping("/student-form/addCourseList")
	public ModelAndView redirectListCourses(Model model) {
		ModelAndView modelAndview = new ModelAndView("student-form");
		List <Branch>listBranches=branchService.listAllBranches();
		List <Course>listCourses =studentService.get(31).getCourses();
	    model.addAttribute("listBranches", listBranches);
	    model.addAttribute("listCourses", listCourses);
	    model.addAttribute("showMyCourseList", 1);
	        return modelAndview;
	} 
	 
	
	@RequestMapping(value ="/student-form/addCourseList1", method = RequestMethod.POST)
	public ModelAndView indexOfStu1dents(Model model,@RequestParam(value="branch",required =true) long id) {
		ModelAndView modelAndview = new ModelAndView("student-form");
		List<Course> listCourses = courseService.listByBranch(id);
		List <Branch>listBranches=branchService.listAllBranches();
	    model.addAttribute("listBranches", listBranches);
		model.addAttribute("listCourses", listCourses);
		model.addAttribute("showMyCourseList", 1);
        return modelAndview;
	}
	
	@GetMapping("/student-form/addStudentList")
	public ModelAndView indexOfStudents(Model model) {
		ModelAndView modelAndview = new ModelAndView("student-form");
	    List<Student> listStudents = studentService.listAll();
	    model.addAttribute("listStudents", listStudents);
	    model.addAttribute("showMyStudentList", 1);
	        return modelAndview;
	} 

}

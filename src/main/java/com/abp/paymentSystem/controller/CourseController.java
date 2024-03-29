package com.abp.paymentSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.abp.paymentSystem.entity.Branch;
import com.abp.paymentSystem.entity.Course;
import com.abp.paymentSystem.service.BranchService;
import com.abp.paymentSystem.service.CourseService;

@Controller
public class CourseController {

@Autowired private CourseService courseService;
@Autowired private BranchService branchService;
	
	
	@RequestMapping("/Course/list-courses")
	public String viewCourses(Model model) {
	    List<Course> listCourses = (List<Course>) courseService.listAllCourses();
	    model.addAttribute("listCourses", listCourses);
	        return "Course/list-courses";
	}
	
	@RequestMapping("/Course/register-course")
	public String showCourses(Model model) {
		Course course = new Course();
		    model.addAttribute("course", course);
		    model.addAttribute("allBranches", branchService.listAllBranches());
		return "Course/register-course";
	}
	
	
	
	@RequestMapping(value = "/savecourse", method = RequestMethod.POST)
	public String saveCourse(@ModelAttribute("course") Course course) {
		courseService.saveCourse(course);
	    return "redirect:/Course/list-courses";
	}
	
	@RequestMapping("/deletecourse/{id}")
	public String deleteCourse(@PathVariable(name = "id") long id) {
		courseService.deleteCourse(id);
	    return "redirect:/";       
	}
	
	@RequestMapping("/editcourse/{id}")
    public String editCourse(@PathVariable(value = "id") int id, Model model) {
                         
		Course course = courseService.getCourse(id);
		  model.addAttribute("course", course);
		    model.addAttribute("allBranches", branchService.listAllBranches());
        return "Course/register-course";
    }
	
}
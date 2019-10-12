package com.abp.paymentSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abp.paymentSystem.entity.Course;
import com.abp.paymentSystem.service.CourseService;

@Controller
public class CourseController {

@Autowired private CourseService courseService;
	
	
	@RequestMapping("/list-courses")
	public String viewCourses(Model model) {
	    List<Course> listCourses = (List<Course>) courseService.listAllCourses();
	    model.addAttribute("listCourses", listCourses);
	        return "list-courses";
	}
	
	@RequestMapping("/register-course")
	public String showCourses(Model model) {
		Course course = new Course();
		    model.addAttribute("course", course);
		return "register-course";
	}
	
	
	
	@RequestMapping(value = "/savecourse", method = RequestMethod.POST)
	public String saveCourse(@ModelAttribute("course") Course course) {
		courseService.saveCourse(course);
	    return "redirect:/";
	}
	
	@RequestMapping("/deletecourse/{id}")
	public String deleteCourse(@PathVariable(name = "id") long id) {
		courseService.deleteCourse(id);
	    return "redirect:/";       
	}

}
package com.abp.paymentSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.abp.paymentSystem.entity.Course;
import com.abp.paymentSystem.entity.Student;
import com.abp.paymentSystem.service.CourseService;
import com.abp.paymentSystem.service.StudentService;

@Controller
public class StController {
	@Autowired
	CourseService courseService;
	@Autowired
	StudentService studentService;
	
	@RequestMapping("/student-form")
	public String showStudent() {
		return "student-form";
	}

	
	@GetMapping("/student-form/addCourseList")
	public ModelAndView redirectListCourses(Model model) {
		ModelAndView modelAndview = new ModelAndView("student-form");
	    List<Course> listCourses = (List<Course>) courseService.listAllCourses();
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

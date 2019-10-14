package com.abp.paymentSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abp.paymentSystem.entity.Student;
import com.abp.paymentSystem.service.StudentService;

@Controller
public class StudentController {

@Autowired private StudentService studentService;
	
	
	@RequestMapping("/Student/list_students")
	public String viewStudents(Model model) {
	    List<Student> listStudents = (List<Student>) studentService.listAll();
	    model.addAttribute("listStudents", listStudents);
	        return "Student/list_students";
	}
	
	@RequestMapping("/Student/register_student")
	public String newStudent(Model model) {
		Student student = new Student();
		    model.addAttribute("student", student);
		return "Student/register_student";
	}
	
	
	
	@RequestMapping(value = "/savestudent", method = RequestMethod.POST)
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.save(student);
	    return "redirect:/Student/list_students";
	}
	
	@RequestMapping("/deletestudent/{id}")
	public String deleteStudent(@PathVariable(name = "id") long id) {
		studentService.delete(id);
	    return "redirect:/";       
	} 
}
package com.abp.paymentSystem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.abp.paymentSystem.entity.Branch;
import com.abp.paymentSystem.entity.Course;
import com.abp.paymentSystem.entity.Faculty;
import com.abp.paymentSystem.entity.Student;
import com.abp.paymentSystem.service.BranchService;
import com.abp.paymentSystem.service.CourseService;
import com.abp.paymentSystem.service.FacultyService;
import com.abp.paymentSystem.service.StudentService;


@Controller
public class StudentController {

@Autowired private StudentService studentService;
@Autowired private CourseService courseService;
@Autowired private BranchService branchService;
@Autowired private FacultyService facultyService;
	
	@RequestMapping("/Student/list_students")
	public String viewStudents(Model model) {
	    List<Student> listStudents = studentService.listAll();
	    model.addAttribute("listStudents", listStudents);
	        return "Student/list_students";
	} 
	
	@RequestMapping("/Student/register")
	public String newStudent(Model model) {
		Student student = new Student();
		    model.addAttribute("student", student);
		return "Student/register";
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
	@RequestMapping("/Student/student-courses")
	public String createStudentWithManyCourses(Model model) {
		model.addAttribute("student",new Student());
		List<Course> courses=courseService.listAllCourses();
		model.addAttribute("courses",courses);
		return "Student/student-courses";
	}
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String saveStudentWithManyCourses(@Valid @ModelAttribute("student") Student student,Model model,@RequestParam(value = "ids" , required = false) int[] ids ,
	         BindingResult bindingResult) {
		
		List<Course> lista=new ArrayList<Course>();
        for(int i : ids) {
			lista.add(courseService.getCourse(i));		
		}
        student.setCourses(lista);
        Branch branch=branchService.getBranch(1);
        Faculty faculty=facultyService.get(1);
        student.setBranch(branch);
        student.setFaculty(faculty);
		studentService.save(student);
		return "redirect:/Student/list_students";
	}
}



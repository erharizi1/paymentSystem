package com.abp.paymentSystem.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.abp.paymentSystem.entity.Branch;
import com.abp.paymentSystem.entity.Course;
import com.abp.paymentSystem.entity.Faculty;
import com.abp.paymentSystem.entity.Role;
import com.abp.paymentSystem.entity.Student;
import com.abp.paymentSystem.entity.StudentCourse;
import com.abp.paymentSystem.entity.User;
import com.abp.paymentSystem.service.BranchService;
import com.abp.paymentSystem.service.CourseService;
import com.abp.paymentSystem.service.FacultyService;
import com.abp.paymentSystem.service.RoleService;

import com.abp.paymentSystem.service.StudentService;
import com.abp.paymentSystem.service.UserService;


@Controller
public class StudentController {

@Autowired private StudentService studentService;
@Autowired private CourseService courseService;
@Autowired private BranchService branchService;
@Autowired private FacultyService facultyService;
@Autowired private RoleService roleService;
@Autowired private UserService userService;

	
	@RequestMapping("/Student/list_students")
	public String viewStudents(Model model) {
	    List<Student> listStudents = studentService.listAll();
	    model.addAttribute("listStudents", listStudents);
	        return "Student/list_students";
	} 
	@RequestMapping("/success")
	public String newview(Model model) {
		return"success";
	}
	
	
	@RequestMapping("/Student/register")
	public String newStudent(Model model) {
		Student student = new Student();
		    model.addAttribute("student", student);
		    model.addAttribute("allBranches", branchService.listAllBranches());
		    return "Student/register";
	}
	
	
	
	@RequestMapping(value = "/savestudent", method = RequestMethod.POST)
	public String saveStudent(@ModelAttribute("student") Student student,@RequestParam(value="branch") long id) {
		 // student.getFaculty().setId(facultyService.findFacultyID(id));
		//student.setFaculty.getId(facultyService.findFacultyID(id));
		 Branch branch=branchService.getBranch(id);
	        Faculty faculty=facultyService.get(branchService.getBranch(id).getFaculty().getId());
	        student.setBranch(branch);
	        student.setFaculty(faculty);
	        
			List<StudentCourse> sc=new ArrayList();
			
			for(Course c:courseService.listByBranch(branch.getId())) {
					StudentCourse studentCourses=new StudentCourse();				
					studentCourses.setStudent(student);
					studentCourses.setCourse(c);
					studentCourses.setPaid(false);
					sc.add(studentCourses);
									
			}
			
			student.setCourses(sc);
			studentService.save(student);
			   
		
		User user=new User();
		user.setEmail(student.getEmail());
		user.setLastname(student.getLastname());
		user.setName(student.getFirstname());
		Role role=roleService.getRole(3);
		Set<Role> roles=new HashSet<>();
		roles.add(role); 
		user.setRole(roles);
		user.setPassword(student.getPassword());		
		userService.saveUser(user);
		
		
	   return "redirect:/student-form";
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
	
	
	
	/*
	 * @RequestMapping(value="/save",method = RequestMethod.POST) public String
	 * saveStudentWithManyCourses(@Valid @ModelAttribute("student") Student
	 * student,Model model,@RequestParam(value = "ids" , required = false) int[] ids
	 * , BindingResult bindingResult) {
	 * 
	 * List<Course> lista=new ArrayList<Course>(); for(int i : ids) {
	 * lista.add(courseService.getCourse(i)); } student.setCourses(lista); Branch
	 * branch=branchService.getBranch(1); Faculty faculty=facultyService.get(1);
	 * student.setBranch(branch); student.setFaculty(faculty);
	 * studentService.save(student); return "redirect:/Student/list_students"; }
	 */
}



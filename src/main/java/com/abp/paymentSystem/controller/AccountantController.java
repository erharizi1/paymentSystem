package com.abp.paymentSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.abp.paymentSystem.entity.Branch;
import com.abp.paymentSystem.entity.Course;
import com.abp.paymentSystem.entity.Faculty;
import com.abp.paymentSystem.entity.Student;
import com.abp.paymentSystem.service.BranchService;
import com.abp.paymentSystem.service.CourseService;
import com.abp.paymentSystem.service.FacultyService;
import com.abp.paymentSystem.service.StudentService;
@PreAuthorize("hasAnyRole('FINANCE')")
@Controller
public class AccountantController {
	
	@Autowired
	private FacultyService facultyService;
	@Autowired 
	BranchService branchService;
	@Autowired
	CourseService courseService;
	@Autowired
	StudentService studentService;
	
	@RequestMapping("/acc-form")
	public String showadmin() {
		return "acc-form";
	}
	
	@RequestMapping(value ="/acc-form/listmybranches", method = RequestMethod.POST)
	public ModelAndView listmybranches(Model model,@RequestParam(value="faculty",required =true) long id) {
		ModelAndView modelAndview = new ModelAndView("acc-form");
		List<Branch> listBranches = branchService.listByFaculty(id);
		List <Faculty>listFaculties=facultyService.listAll();
	    model.addAttribute("listFaculties", listFaculties);
		model.addAttribute("listBranches", listBranches);
		model.addAttribute("showBranchList", 1);
        return modelAndview;
	}

	@GetMapping("/acc-form/addBranchList")
	public ModelAndView redirectListBranches(Model model) {
		ModelAndView modelAndview = new ModelAndView("acc-form");
	    List<Branch> listBranches = (List<Branch>) branchService.listAllBranches();
	    model.addAttribute("listBranches", listBranches);
	    List <Faculty>listFaculties=facultyService.listAll();
	    model.addAttribute("listFaculties", listFaculties);
	    model.addAttribute("showBranchList", 1);
	        return modelAndview;
	}
	
	@GetMapping("/acc-form/addCourseForm")
  public ModelAndView redirectCourseForm(Model model) {
		ModelAndView modelAndview = new ModelAndView("acc-form");
		model.addAttribute("course", new Course());
		model.addAttribute("allBranches", branchService.listAllBranches());
      model.addAttribute("showCourssForm", 1);
      return modelAndview;
	}
	
	@RequestMapping("/acc-form/addCourse")
	public ModelAndView redirectSaveCourses(@ModelAttribute("course") Course course,ModelMap model ) {
		courseService.saveCourse(course);
		return new ModelAndView("forward:/acc-form", model);
	}
	
	@GetMapping("/acc-form/addCourseList")
	public ModelAndView redirectListCourses(Model model) {
		ModelAndView modelAndview = new ModelAndView("acc-form");
	    List<Course> listCourses = (List<Course>) courseService.listAllCourses();
	    model.addAttribute("listCourses", listCourses);
	    model.addAttribute("showCourseList", 1);
	        return modelAndview;
	}
	
	@GetMapping("/acc-form/addStudentList")
	public ModelAndView indexOfStudents(Model model) {
		ModelAndView modelAndview = new ModelAndView("acc-form");
	    List<Student> listStudents = studentService.listAll();
	    model.addAttribute("listStudents", listStudents);
	    model.addAttribute("showStudentDetails", 1);
	        return modelAndview;
	} 

}

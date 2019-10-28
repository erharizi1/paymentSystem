package com.abp.paymentSystem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.abp.paymentSystem.entity.Branch;
import com.abp.paymentSystem.entity.Course;
import com.abp.paymentSystem.entity.Faculty;
import com.abp.paymentSystem.entity.Finance;
import com.abp.paymentSystem.entity.Student;
import com.abp.paymentSystem.entity.StudentCourse;
import com.abp.paymentSystem.service.BranchService;
import com.abp.paymentSystem.service.CourseService;
import com.abp.paymentSystem.service.FacultyService;
import com.abp.paymentSystem.service.FinanceService;
import com.abp.paymentSystem.service.StudentService;
//@PreAuthorize("hasAnyRole('FINANCE')")
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
	@Autowired
	FinanceService financeService;
	
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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Finance finance=financeService.findFinanceByEmail(auth.getName());
		Faculty faculty=facultyService.get(finance.getFaculty().getId());
		System.out.println(faculty.getName());
		
		List<Branch> branches=branchService.listAllBranches();
		List<Branch> branchOfFinance=new ArrayList();
		for(Branch b:branches) {
			if(b.getFaculty().getId()==faculty.getId())
				branchOfFinance.add(b);
		}
		ModelAndView modelAndview = new ModelAndView("acc-form");
	    
	    model.addAttribute("listBranches", branchOfFinance);
	    List <Faculty>listFaculties=facultyService.listAll();
	    model.addAttribute("listFaculties", listFaculties);
	    model.addAttribute("showBranchList", 1);
	        return modelAndview;
	}
	
	@GetMapping("/acc-form/addCourseForm")
  public ModelAndView redirectCourseForm(Model model) {
		ModelAndView modelAndview = new ModelAndView("acc-form");
		model.addAttribute("course", new Course());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Finance finance=financeService.findFinanceByEmail(auth.getName());
		Faculty faculty=facultyService.get(finance.getFaculty().getId());
		System.out.println(faculty.getName());
		
		List<Branch> branches=branchService.listAllBranches();
		List<Branch> branchOfFinance=new ArrayList();
		for(Branch b:branches) {
			if(b.getFaculty().getId()==faculty.getId())
				branchOfFinance.add(b);
		}
		model.addAttribute("allBranches",branchOfFinance);
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

	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Finance finance=financeService.findFinanceByEmail(auth.getName());
	    List<Student> studentOfMyFaculty=new ArrayList();
	    for(Student s:listStudents) {
	    	if(s.getFaculty().getId()==finance.getFaculty().getId())
	    		studentOfMyFaculty.add(s);
	    }
	    model.addAttribute("listStudents", studentOfMyFaculty);

	    

	    model.addAttribute("showStudentDetails", 1);
	        return modelAndview;
	}
	/*
	@RequestMapping("/studentPayment/{id}")
	public String showPayment(@PathVariable(name = "id") long id,Model model) {
		Student student=studentService.get(id);
		List<StudentCourse> sc=new ArrayList();
		for(StudentCourse ss:student.getCourses()) {
			if(ss.getPaid()==false)
				sc.add(ss);
		}
		List<Course> courses=new ArrayList();
		for(StudentCourse s:sc) {
			Course course=s.getCourse();
			courses.add(course);
		}
		model.addAttribute("courses",courses);
		model.addAttribute("student",student);
		return "studentPayment";
	}
	*/
	@RequestMapping("/studentPayment/{id}")
	public String showPayment(@PathVariable(name = "id") long id,Model model) {
		Student student=studentService.get(id);
		List<StudentCourse> sc=new ArrayList();
		for(StudentCourse ss:student.getCourses()) {
			if(ss.getPaid()==false)
				sc.add(ss);
		}
		
		model.addAttribute("courses",sc);
		model.addAttribute("student",student);
		return "studentPayment";
	}
	
	@RequestMapping(value="/savePayment",method = RequestMethod.POST) 
	public String saveStudentPayments(
			
			Model model,@RequestParam(value = "ids" , required = false) int[] ids,
			@RequestParam(value="studentId",required=false)Long studentId ) {
		
	  Student student=studentService.get(studentId);
	  List<StudentCourse> lista=new ArrayList<StudentCourse>(); 
	  for(int i : ids) {
	  Course course=courseService.getCourse(i);
	  StudentCourse sc=new StudentCourse();
	  sc.setCourse(course);
	  sc.setPaid(true);
	  sc.setStudent(student);
	  lista.add(sc);
	  } 
	  student.setCourses(lista);	  
	  studentService.save(student); 
	  return "redirect:/acc-form"; }
	 
	

}

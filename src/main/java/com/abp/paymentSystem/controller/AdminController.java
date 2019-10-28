package com.abp.paymentSystem.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.abp.paymentSystem.entity.Branch;
import com.abp.paymentSystem.entity.Faculty;
import com.abp.paymentSystem.entity.Finance;
import com.abp.paymentSystem.entity.Role;
import com.abp.paymentSystem.entity.Student;
import com.abp.paymentSystem.entity.User;
import com.abp.paymentSystem.service.BranchService;
import com.abp.paymentSystem.service.CourseService;
import com.abp.paymentSystem.service.FacultyService;
import com.abp.paymentSystem.service.FinanceService;
import com.abp.paymentSystem.service.RoleService;
import com.abp.paymentSystem.service.StudentService;
import com.abp.paymentSystem.service.UserService;

//@PreAuthorize("hasAnyRole('ADMIN')")
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
	@Autowired
	private RoleService roleSerivce;
	@Autowired
	private UserService userService;
	
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
//		Faculty faculty=facultyService.get(1);
//		faculty.setBranches((List<Branch>) branch);
//		facultyService.save(faculty);
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
	
	@RequestMapping("/admin-form/deletebranch/{id}")
	public String deleteBranch(@PathVariable(name = "id") long id) {
	    branchService.deleteBranch(id);
	    return "redirect:/";       
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
		User user=new User();
		user.setEmail(finance.getEmail());
		user.setName(finance.getName());
		user.setLastname(finance.getLastname());
		user.setPassword(finance.getPassword());		
		
		  Role role= roleSerivce.getRole(2);
		  Set<Role> roles=new HashSet<>();
		  roles.add(role); user.setRole(roles);
		 
		userService.saveUser(user);
		return new ModelAndView("forward:/admin-form", model);
	}
	
	@RequestMapping("/admin-form/saveFinanceEdit")
	public ModelAndView SaveFinance(@ModelAttribute("finance") Finance finance,ModelMap model ) {
		Finance f=financeService.getFinance(finance.getId());
		Faculty fac=f.getFaculty();
		finance.setFaculty(fac);				
		financeService.saveFinance(finance);
		User user=userService.getUser(finance.getEmail());
//		Faculty faculty=facultyService.get(finance.getFaculty().getId());
//		faculty.getFinaces().add(finance);
//		facultyService.save(faculty);
		
		user.setEmail(finance.getEmail());
		user.setName(finance.getName());
		user.setLastname(finance.getLastname());
		user.setPassword(finance.getPassword());		
		
		  Role role= roleSerivce.getRole(2);
		  Set<Role> roles=new HashSet<>();
		  roles.add(role); user.setRole(roles);
		 
		userService.saveUser(user);
		return new ModelAndView("forward:/admin-form", model);
	}
	
	
		
	
	@GetMapping("/admin-form/addFinanceList")
	public ModelAndView indexOfFinance (Model model) {
		ModelAndView modelAndview = new ModelAndView("admin-form");
		List<Finance> allFinances=(List<Finance>)financeService.listAllFinances();
		model.addAttribute("allFinances",allFinances);
		List <Faculty>listFaculty=facultyService.listAll();
	    model.addAttribute("listFaculty", listFaculty);
		 model.addAttribute("showFinancesList", 1);
		 return modelAndview;
	}
	
	@RequestMapping(value ="/admin-form/addFinanceList1", method = RequestMethod.POST)
	public ModelAndView indexOfFinance1(Model model, @RequestParam(value="faculty",required =true) long id) {
		ModelAndView modelAndview = new ModelAndView("admin-form");
		List<Finance> listFinance = (List<Finance>) financeService.listByFaculty(id);
		List <Faculty>listFaculty=facultyService.listAll();
	    model.addAttribute("listFaculty", listFaculty);
		model.addAttribute("listFinance", listFinance);
		 model.addAttribute("showFinancesList", 1);
        return modelAndview;
	}
	
	@GetMapping("/admin-form/addStudentList")
	public ModelAndView indexOfStudents(Model model) {
		ModelAndView modelAndview = new ModelAndView("admin-form");
	    List<Student> listStudents = (List<Student>) studentService.listAll();
	    model.addAttribute("listStudents", listStudents);
	    List <Branch>listBranches=branchService.listAllBranches();
	    model.addAttribute("listBranches", listBranches);
	    model.addAttribute("showStudentList", 1);
	        return modelAndview;
	} 
	
	
	
	
	
	@RequestMapping(value ="/admin-form/addStudentList1", method = RequestMethod.POST)
	public ModelAndView indexOfStudents1(Model model, @RequestParam(value="branch",required =true) long id) {
		ModelAndView modelAndview = new ModelAndView("admin-form");
		List<Student> listStudents = (List<Student>) studentService.listByBranch(id);
		List <Branch>listBranches=branchService.listAllBranches();
	    model.addAttribute("listBranches", listBranches);
		model.addAttribute("listStudents", listStudents);
		 model.addAttribute("showStudentList", 1);
        return modelAndview;
	}
	
	@RequestMapping("/admin-form/edit/{id}")
	public ModelAndView showEditFinancePage(@PathVariable(name = "id") long id) {
	    ModelAndView mav = new ModelAndView("edit_finance");
	    Finance finance=financeService.getFinance(id);
	    mav.addObject("finance", finance);	     
	    return mav;
	}
	
	@RequestMapping("/admin-form/delete/{id}")
	public String deleteFinance(@PathVariable(name = "id") long id) {
		String email=financeService.getFinance(id).getEmail();
		User user=userService.getUser(email);
	    financeService.deleteFinance(id);
	    userService.delete(user.getId());
	    
	    return "redirect:/";       
	}
}

package com.abp.paymentSystem.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="email")
	private String email;
	
	@Column(name="addres")
	private String addres;
	@ManyToOne
	@JoinColumn(nullable=false, name = "faculty_id")
	private Faculty faculty;
	@ManyToOne
	@JoinColumn(nullable=false, name = "branch_id")
	private Branch branch;
	@ManyToMany
	@JoinTable(name="course_student",joinColumns=@JoinColumn(name="student_ID"),inverseJoinColumns=@JoinColumn(name="course_ID"))
	private List<Course> courses;
	
	
	public Student() {}
	
	public Student(String firstname, String lastname, String email, String addres, Faculty faculty, Branch branch) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.addres = addres;
		this.faculty = faculty;
		this.branch = branch;
	}


<<<<<<< Updated upstream

	

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public long getStudent_id() {
		return student_id;
=======
	public long getId() {
		return id;
>>>>>>> Stashed changes
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddres() {
		return addres;
	}


	public void setAddres(String addres) {
		this.addres = addres;
	}


	public Faculty getFaculty() {
		return faculty;
	}


	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}


	public Branch getBranch() {
		return branch;
	}


	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
}

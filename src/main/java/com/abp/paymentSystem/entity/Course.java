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
import javax.persistence.OrderBy;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="course_id")
	@OrderBy("id DESC")
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private String price;
	
	
//	  @ManyToOne	  
//	  @JoinColumn(name = "branch_id", referencedColumnName = "branch_id") private
//	  Set<Branch> branch;
//	 
	
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private Branch branch;
	@ManyToMany
	@JoinTable(name="course_student",joinColumns=@JoinColumn(name="course_ID"),inverseJoinColumns=@JoinColumn(name="student_ID"))
	private List<Student> students;
	
	public Course() {
		
	}

	
	public List<Student> getStudents() {
		return students;
	}


	public void setStudents(List<Student> students) {
		this.students = students;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getprice() {
		return price;
	}


	public void setprice(String price) {
		this.price = price;
	}


	public Branch getBranch() {
		return branch;
	}


	public void setBranch(Branch branch) {
		this.branch = branch;
	}


	public Course(String name, String price, Branch branch) {
		this.name = name;
		this.price = price;
		this.branch = branch;
	}

}

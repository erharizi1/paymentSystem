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
	private long courseId;
	
	@Column(name="course_name")
	private String courseName;
	
	@Column(name="course_price")
	private String coursePrice;
	
	
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


	public long getCourseId() {
		return courseId;
	}


	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getCoursePrice() {
		return coursePrice;
	}


	public void setCoursePrice(String coursePrice) {
		this.coursePrice = coursePrice;
	}


	public Branch getBranch() {
		return branch;
	}


	public void setBranch(Branch branch) {
		this.branch = branch;
	}


	public Course(String courseName, String coursePrice, Branch branch) {
		this.courseName = courseName;
		this.coursePrice = coursePrice;
		this.branch = branch;
	}

}

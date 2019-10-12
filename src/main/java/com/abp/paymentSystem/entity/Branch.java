package com.abp.paymentSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;

@Entity
public class Branch {
	
//	@OneToMany(mappedBy="branch")
//    private List<Course> course;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="branch_id")
	@OrderBy("id DESC")
	private long branchId;
	
	@Column(name="branch_name")
	private String branchName;
	
	@ManyToOne
	@JoinColumn(nullable=false, name = "faculty_id")
	private Faculty faculty;
		
	public Branch() {
		
	}

	public long getBranchId() {
		return branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Branch(String branchName, Faculty faculty) {
		super();
		this.branchName = branchName;
		this.faculty = faculty;
	}

	

}

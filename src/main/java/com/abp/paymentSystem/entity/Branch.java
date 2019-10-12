package com.abp.paymentSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	public Branch(String branchName) {
		super();
		this.branchName = branchName;
	}

}

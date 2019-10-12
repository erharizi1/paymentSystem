package com.abp.paymentSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Faculty {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="faculty_id")
	private long faculty_id;
	@Column(name="name")
	private String name;
	
	public Faculty() {}
	
	
	
	public Faculty(String name) {
		this.name = name;
	}



	public long getFaculty_id() {
		return faculty_id;
	}
	public void setFaculty_id(long faculty_id) {
		this.faculty_id = faculty_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

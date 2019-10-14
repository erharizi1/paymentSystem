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
	private long facultyId;
	@Column(name="faculty_name")
	private String facultyName;
	
	public Faculty() {}
	
	public Faculty(String facultyName) {
		this.facultyName = facultyName;
	}

	public long getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(long facultyId) {
		this.facultyId = facultyId;
	}
	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getfacultyName() {
		return facultyName;
	}
	public void setfacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	
	
}

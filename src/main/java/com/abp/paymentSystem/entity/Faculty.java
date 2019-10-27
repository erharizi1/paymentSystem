package com.abp.paymentSystem.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Faculty {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name="name")
	private String name;
	@OneToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
					CascadeType.DETACH,CascadeType.REFRESH})
	
	private List<Finance> finaces;
	@OneToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
			CascadeType.DETACH,CascadeType.REFRESH})
	
	private List<Branch> branches;
	
	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public Faculty(String name, List<Finance> finaces) {
		super();
		this.name = name;
		
	}

	public Faculty() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public List<Finance> getFinaces() {
		return finaces;
	}
	public void setFinaces(List<Finance> finaces) {
		this.finaces = finaces;
	}
	@Override
	public String toString() {
		return "Faculty [id=" + id + ", name=" + name + "]";
	}
	
	
}

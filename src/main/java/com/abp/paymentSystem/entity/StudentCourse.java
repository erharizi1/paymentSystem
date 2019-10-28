package com.abp.paymentSystem.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;


@Entity
public class StudentCourse implements Serializable {
	
	@Id
    @ManyToOne
    @JoinColumn
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn
    private Course course;
    
    
    private Boolean paid;
    
    public StudentCourse() {
    	
    }

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentCourse)) return false;
        StudentCourse that = (StudentCourse) o;
        return Objects.equals(student.getFirstname(), that.student.getFirstname()) &&
                Objects.equals(course.getName(), that.course.getName()) &&
                Objects.equals(paid, that.paid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student.getFirstname(), course.getName(), paid);
    }

}

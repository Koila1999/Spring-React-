package com.example.demo.Model;

import java.sql.Date;



import org.hibernate.annotations.NotFound;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="NDCApplyForm")
public class Ndcapply_Form {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long formId;
	@Column(name="studentName",nullable=false)
	private String stdName;
	@Column(name="dept")
	private String department;
	@Column(name="semester")
	private String semester;
	@Column(name="year")
	private String sessionYr;
	@Column(name="submit_date")
	private Date submissionDate;
	@Column(name="reason")
	private String ReasonFor;
	@Column(name="status")
	private String status;
//	@Column(name="Sign_img")
//	private MultipartFile sign;
	
	@OneToOne
	@JsonManagedReference
	private Student student;
	
	public Ndcapply_Form() {
	
	}
	
	

	public Ndcapply_Form(Long formId, String stdName, String department, String semester, String sessionYr,
			Date submissionDate, String reasonFor, String status, Student student) {
		super();
		this.formId = formId;
		this.stdName = stdName;
		this.department = department;
		this.semester = semester;
		this.sessionYr = sessionYr;
		this.submissionDate = submissionDate;
		ReasonFor = reasonFor;
		this.status = status;
		this.student = student;
	}



	public Long getFormId() {
		return formId;
	}

	public void setFormId(Long formId) {
		this.formId = formId;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getSessionYr() {
		return sessionYr;
	}

	public void setSessionYr(String sessionYr) {
		this.sessionYr = sessionYr;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getReasonFor() {
		return ReasonFor;
	}

	public void setReasonFor(String reasonFor) {
		ReasonFor = reasonFor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}



	@Override
	public String toString() {
		return "Ndcapply_Form [formId=" + formId + ", stdName=" + stdName + ", department=" + department + ", semester="
				+ semester + ", sessionYr=" + sessionYr + ", submissionDate=" + submissionDate + ", ReasonFor="
				+ ReasonFor + ", status=" + status + ", student=" + student + "]";
	}
	
	
}

	
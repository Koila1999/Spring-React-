package com.example.demo.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="Certificate")
public class Certificate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name="studentid")
	private Student student;
	@Column(unique = true,nullable = false)
	private String certificationNumber;
	@Column(nullable = false)
	private Date dateOfissue;
	public Certificate(Long id, Student student, String certificationNumber, Date dateOfissue) {
		super();
		this.id = id;
		this.student = student;
		this.certificationNumber = certificationNumber;
		this.dateOfissue = dateOfissue;
	}
	public Certificate() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getCertificationNumber() {
		return certificationNumber;
	}
	public void setCertificationNumber(String certificationNumber) {
		this.certificationNumber = certificationNumber;
	}
	public Date getDateOfissue() {
		return dateOfissue;
	}
	public void setDateOfissue(Date dateOfissue) {
		this.dateOfissue = dateOfissue;
	}
	
	
	
	
	
}

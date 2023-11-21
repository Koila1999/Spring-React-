package com.example.demo.Services3;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

//import java.util.Optional;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.UnauthorizedAccessException;
import com.example.demo.Model.DepartmentAdmin;
import com.example.demo.Model.LoginUser;
import com.example.demo.Model.Ndcapply_Form;

import com.example.demo.Model.Student;

import com.example.demo.Repository.DepartmentAdminRepository;
import com.example.demo.Repository.LoginUserRepository;
import com.example.demo.Repository.NoduesFormRepository;
import com.example.demo.Repository.NoduesRepository;

@Service
public class implNoduesServices implements NoduesServices{
	
	@Autowired
	private CertificateRepository certificateRepository;
	
	

	

	@Override
	public Certificate generateCertificate(Long studentId) {
		Student student=noduesRepository.findById(studentId)
				.orElseThrow(()->new NoSuchElementException("Stduent not found with ID:"+studentId));
		String certificateNumber=generateRandomCertificateNumber();
		Date datafoIssue=new Date();
		Certificate certificate=new Certificate();
		certificate.setStudent(student);
		certificate.setCertificationNumber(certificateNumber);
		certificate.setDateOfissue(datafoIssue);
		return certificateRepository.save(certificate);
	}
	 private String generateRandomCertificateNumber() {
	        return UUID.randomUUID().toString().substring(0, 8);
	    }



	@Override
	public Certificate getCertificateByStudentId(Long StudentId) {
		Optional<Certificate> certificate=certificateRepository.findByStudentId(StudentId);
		if(certificate.isPresent()) {
			return certificate.get();
		}
		
		return certificate.orElseThrow(() -> new NoSuchElementException("Certificate not found with ID: " + StudentId));
	}

	

}
package com.example.demo.Controller;

import java.util.List;

import javax.security.auth.login.LoginContext;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.DepartmentAdmin;
import com.example.demo.Model.LoginUser;
import com.example.demo.Model.Ndcapply_Form;

import com.example.demo.Model.Student;
import com.example.demo.Repository.LoginUserRepository;
import com.example.demo.Services3.NoduesServices;
import com.example.demo.Services3.StudentIdService;
@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/api/nodues") //url 
public class NDCController {
	
	@Autowired
	private NoduesServices noduesServices;

	public NDCController(NoduesServices noduesServices) {
		super();
		this.noduesServices = noduesServices;
		
	}
	@Autowired
	private StudentIdService studentIdService;
	
	public NDCController(StudentIdService studentIdService) {
		super();
		this.studentIdService = studentIdService;
	}




//	deafault constructor
	public NDCController() {
		
	}

		//certification operation
	@GetMapping("generate-certificate/{studentId}")
	public ResponseEntity<Certificate>saveCertificate(@PathVariable Long studentId){
		Certificate certificate=noduesServices.generateCertificate(studentId);
		if(certificate != null) {
			return new ResponseEntity<Certificate>(certificate,HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	@GetMapping("/download/{StudentId}")
	public ResponseEntity<Certificate>downloadCertificate(@PathVariable Long StudentId){
		return new ResponseEntity<Certificate>(noduesServices.getCertificateByStudentId(StudentId),HttpStatus.OK);
	}


	
	

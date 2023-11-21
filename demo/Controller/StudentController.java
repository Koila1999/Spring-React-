package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.AutheticationService;

import dto.StudentLoginRequest;
import dto.StudentLoginResponse;
import dto.StudentRegisterRequest;

@RestController
@RequestMapping("/api/v1/auth")
public class StudentController {
	@Autowired
	private final AutheticationService autheticationService;

	public StudentController(AutheticationService autheticationService) {
		super();
		this.autheticationService = autheticationService;
	}

	@PostMapping("/register")
	public ResponseEntity<StudentLoginResponse>register(@RequestBody StudentRegisterRequest request)
	{
		return ResponseEntity.ok(autheticationService.register(request));
	//	
	}

	@PostMapping("/authenticate")
	public ResponseEntity<StudentLoginResponse>authenticate(@RequestBody StudentLoginRequest request)
	{
		return ResponseEntity.ok(autheticationService.authenticate(request));
		
	}

}

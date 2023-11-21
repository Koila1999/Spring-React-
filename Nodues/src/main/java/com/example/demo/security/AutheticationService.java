package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import com.example.demo.Model.Role;
import com.example.demo.Model.Student;
import com.example.demo.Repository.AdminRepository;
import com.example.demo.Repository.DepartmentAdminRepository;
import com.example.demo.Repository.NoduesRepository;

import dto.StudentLoginRequest;
import dto.StudentLoginResponse;
import dto.StudentRegisterRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutheticationService {
	@Autowired
	private final NoduesRepository noduesRepository;
	@Autowired
	private final JWTService jwtService;
	@Autowired
	private final AuthenticationManager autheman;
	@Autowired
	private final PasswordEncoder passwordEncoder;
	
	
	



public AutheticationService(NoduesRepository noduesRepository, JWTService jwtService,
			AuthenticationManager autheman, PasswordEncoder passwordEncoder) {
		super();
		this.noduesRepository = noduesRepository;
		this.jwtService = jwtService;
		this.autheman = autheman;
		this.passwordEncoder = passwordEncoder;
	}

public StudentLoginResponse register(StudentRegisterRequest request) {
	Student user=new Student();
	user.setFirstName(request.getFirstname());
	user.setLastName(request.getLastname());
	user.setBranch(request.getBranch());
	user.setEmail(request.getEmail());
	user.setSessionYear(request.getSessionYr());
	user.setPassword(passwordEncoder.encode(request.getPassword()));
	user.setRole(Role.STUDENT);		
            

    noduesRepository.save(user);
    String jwtToken=jwtService.generateToken(user);
    
	return new StudentLoginResponse(jwtToken);
}
@Bean
public StudentLoginResponse authenticate(StudentLoginRequest request) {
	// TODO Auto-generated method stub
	autheman.authenticate(
			new UsernamePasswordAuthenticationToken(
					request.getEmail(), 
					request.getPassword())
			);
	Student user=noduesRepository.findByEmail(request.getEmail())
			.orElseThrow();
	String jwtToken=jwtService.generateToken(user);
	return new StudentLoginResponse(jwtToken);
}



}

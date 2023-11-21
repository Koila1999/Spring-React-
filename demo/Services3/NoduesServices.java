 package com.example.demo.Services3;

import java.util.List;

import org.springframework.stereotype.Service;





import com.example.demo.Model.Student;
@Service
public interface NoduesServices {


		//generate certificate for the student
	 
	Certificate generateCertificate(Long StudentId);
	Certificate getCertificateByStudentId(Long StudentId);
	
	


	
	
	
}

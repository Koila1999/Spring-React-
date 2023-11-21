package com.example.demo.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Model.DepartmentAdmin;
import com.example.demo.Model.NoduesAdmin;
import com.example.demo.Model.Student;
import com.example.demo.Repository.AdminRepository;
import com.example.demo.Repository.DepartmentAdminRepository;
import com.example.demo.Repository.NoduesRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
	
	private final NoduesRepository noduesRepository;
	
	private final DepartmentAdminRepository departmentAdminRepository;
	private final AdminRepository adminRepository;
	
	public ApplicationConfig(NoduesRepository noduesRepository, DepartmentAdminRepository departmentAdminRepository,
			AdminRepository adminRepository) {
		super();
		this.noduesRepository = noduesRepository;
		this.departmentAdminRepository = departmentAdminRepository;
		this.adminRepository = adminRepository;
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				UserDetails  userDetails1 = retrieveUserDetailsFromDepartmentAdminRepository(username);
				UserDetails userDetails = retrieveUserDetailsFromNoduesRepository(username);
	               UserDetails userDetails2 = retrieveUserDetailsFromAdminRepository(username);

                if (userDetails != null) {
                    return userDetails;
                }

                else if (userDetails1 != null) {
                    return userDetails1;
                }

                else if (userDetails2 != null) {
                    return userDetails2;
                }

                else{
                	throw new UsernameNotFoundException("User with email " + username + " not found");
                }
            }
			
			@Bean
			public AuthenticationProvider authenticationProvider() {
				DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
				authProvider.setUserDetailsService(userDetailsService());
				authProvider.setPasswordEncoder(passwordEncoder());
				return authProvider;
			}
			
			@Bean
			public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
				return config.getAuthenticationManager();
			}
			
			@Bean
			 public PasswordEncoder passwordEncoder() {
				return new BCryptPasswordEncoder();
			}

            private UserDetails retrieveUserDetailsFromNoduesRepository(String username) {
            	Optional<Student> studentOptional = noduesRepository.findByEmail(username);
                if (studentOptional.isPresent()) {
                    Student student = studentOptional.get();
            	
                return student;
            }
                	return null;
                
            }
            private UserDetails retrieveUserDetailsFromDepartmentAdminRepository(String username) {
               Optional<DepartmentAdmin>dept= departmentAdminRepository.findByEmail(username);
               if(dept.isPresent()) {
            	   DepartmentAdmin departmentAdmin=dept.get();
            	   return departmentAdmin;
               }
			return null;
               
            }

            private UserDetails retrieveUserDetailsFromAdminRepository(String username) {
            	Optional<NoduesAdmin>admin=adminRepository.findByEmail(username);
            	if(admin.isPresent()) {
            		NoduesAdmin noduesAdmin=admin.get();
            		return noduesAdmin;
            	}
				return null;
            }
						
			
		};
		
	}
}

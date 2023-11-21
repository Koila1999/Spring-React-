package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.Model.Student;

import jakarta.annotation.security.PermitAll;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	
	
	private AuthenticationProvider authenticationProvider;
	private final JwtAuthenticationFilter authenticationFilter;
	
	public SecurityConfiguration(JwtAuthenticationFilter authenticationFilter) {
		super();
		this.authenticationFilter = authenticationFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

       //configuration
		
		http
			.csrf(csrf->csrf.disable())
			.cors(cors->cors.disable())
			.authorizeHttpRequests(
					auth->
						auth.requestMatchers("/api/v1/auth/**")
						.authenticated()
//						.PermitAll()
//						.requestMatchers("/student")
//						.hasAnyRole("STUDENT")
//						.requestMatchers("/department-admin")
//						.hasAnyRole("DEPARTMENT_ADMIN")
						.anyRequest()
						.authenticated()
						
				
				)
						.sessionManagement(sess->
						sess
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						.and()
						.authenticationProvider(authenticationProvider))
						.addFilterBefore(authenticationFilter,OncePerRequestFilter.class);
								
						
		
						
						
						
					
						
					
			
		return http.build();
	}
	
	
}
	//we can pass our application patterns
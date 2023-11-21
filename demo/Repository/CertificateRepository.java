package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long>{
	Optional<Certificate> findByStudentId(Long studentId);
}

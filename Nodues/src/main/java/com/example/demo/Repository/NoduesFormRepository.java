package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Ndcapply_Form;

public interface NoduesFormRepository extends JpaRepository<Ndcapply_Form, Long> {

	Ndcapply_Form findByStudentId(Long studentId);
}

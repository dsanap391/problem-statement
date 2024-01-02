package com.mf.ps.problemstatement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mf.ps.problemstatement.model.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead,Integer>{

	List<Lead> findByMobileNumber(String mobileNumber);
}

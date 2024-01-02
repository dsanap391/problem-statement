package com.mf.ps.problemstatement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mf.ps.problemstatement.model.Lead;
import com.mf.ps.problemstatement.repository.LeadRepository;

@Service
public class LeadService {
	@Autowired
	private LeadRepository leadRepository;

	public Lead createLead(Lead lead) {
		return leadRepository.save(lead);
	}

	public List<Lead> getAllLeads() {
		return leadRepository.findAll();
	}

	public boolean isLeadIdExists(int leadId) {
		return leadRepository.existsById(leadId);
	}

	public List<Lead> getLeadByMobileNumber(String mobileNumber) {
		return leadRepository.findByMobileNumber(mobileNumber);
	}
}

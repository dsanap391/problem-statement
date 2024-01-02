package com.mf.ps.problemstatement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mf.ps.problemstatement.exception.LeadAlreadyExistsException;
import com.mf.ps.problemstatement.model.Lead;
import com.mf.ps.problemstatement.repository.LeadRepository;
import com.mf.ps.problemstatement.service.LeadService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

	@Autowired
	private LeadRepository leadRepository;

	@Autowired
	private LeadService leadService;

	@PostMapping
	public ResponseEntity<?> createLead(@Valid @RequestBody Lead lead, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}

		if (leadService.isLeadIdExists(lead.getLeadId())) {
			throw new LeadAlreadyExistsException(
					"Lead Already Exists in the database with the lead id " + lead.getLeadId());
		}

		Lead createdLead = leadRepository.save(lead);
		return new ResponseEntity<>(createdLead, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Lead>> getAllLeads() {
		List<Lead> leads = leadRepository.findAll();
		return new ResponseEntity<>(leads, HttpStatus.OK);
	}

	@ExceptionHandler
	public ResponseEntity<?> handleException(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
    @GetMapping("/mobileNumber")
	public ResponseEntity<Object> getLeadsByMobileNumber(@PathVariable String mobileNumber){
		List<Lead> leads = leadService.getLeadByMobileNumber(mobileNumber);
		if(leads.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No leads found with mobile number: " +mobileNumber);
		}
		return ResponseEntity.ok(leads);
	}
}

package com.mf.ps.problemstatement.leadservicertest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mf.ps.problemstatement.controller.LeadController;
import com.mf.ps.problemstatement.model.Lead;
import com.mf.ps.problemstatement.repository.LeadRepository;
import com.mf.ps.problemstatement.service.LeadService;

@RunWith(MockitoJUnitRunner.class)
public class LeadServiceTest {
	
	@InjectMocks
	private LeadController leadController;
	
	@Mock
	private LeadService leadService;
	
	@MockBean
	private LeadRepository leadRepository;	
	
	@Test
    void testGetAllLeads() {
        // Arrange
        Lead lead1 = new Lead();
        lead1.setLeadId(1003);
        lead1.setFirstName("Jerry");
        lead1.setMiddleName("johnson");
        lead1.setLastName("Trump");
       // lead1.setDateOfBirth("2001-10-02 05:30:00.000000");;
        lead1.setMobileNumber("7234567890");
        lead1.setGender("FEMALE");
        lead1.setEmail("jerry@gmail.com");

        Lead lead2 = new Lead();
        lead2.setLeadId(1004);
        lead2.setFirstName("Jenny");
        lead2.setMiddleName("johnson");
        lead2.setLastName("Watson");
       // lead1.setDateOfBirth("2001-10-02 05:30:00.000000");;
        lead2.setMobileNumber("7234567890");
        lead2.setGender("FEMALE");
        lead2.setEmail("jenny@gmail.com");

        List<Lead> expectedLeads = Arrays.asList(lead1, lead2);

        when(leadRepository.findAll()).thenReturn(expectedLeads);

        List<Lead> actualLeads = leadService.getAllLeads();

        assertEquals(expectedLeads, actualLeads);

        verify(leadRepository, times(1)).findAll();
    }
	
	@Test
    void testGetLeadByMobileNumber() {
        String mobileNumber = "1234567890";
        Lead lead1 = new Lead();
        Lead lead2 = new Lead();
        List<Lead> expectedLeads = Arrays.asList(lead1, lead2);

        when(leadRepository.findByMobileNumber(mobileNumber)).thenReturn(expectedLeads);

        List<Lead> actualLeads = leadService.getLeadByMobileNumber(mobileNumber);

        assertEquals(expectedLeads, actualLeads);

        verify(leadRepository, times(1)).findByMobileNumber(mobileNumber);
    }
	
	@Test
    void testCreateLead() {
        Lead inputLead = new Lead();
        inputLead.setLeadId(1003);
        inputLead.setFirstName("Jerry");
        inputLead.setMiddleName("johnson");
        inputLead.setLastName("Trump");
       // inputLead.setDateOfBirth("2001-10-02 05:30:00.000000");;
        inputLead.setMobileNumber("7234567890");
        inputLead.setGender("FEMALE");
        inputLead.setEmail("jerry@gmail.com");

        Lead savedLead = new Lead();
        savedLead.setLeadId(1003);
        savedLead.setFirstName("Jerry");
        savedLead.setMiddleName("johnson");
        savedLead.setLastName("Trump");
       // lead1.setDateOfBirth("2001-10-02 05:30:00.000000");;
        savedLead.setMobileNumber("7234567890");
        savedLead.setGender("FEMALE");
        savedLead.setEmail("jerry@gmail.com");

        when(leadRepository.save(inputLead)).thenReturn(savedLead);

        Lead createdLead = leadService.createLead(inputLead);

        assertEquals(savedLead, createdLead);

        verify(leadRepository, times(1)).save(inputLead);
    }
}

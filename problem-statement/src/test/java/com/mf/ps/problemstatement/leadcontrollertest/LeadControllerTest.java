package com.mf.ps.problemstatement.leadcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.mf.ps.problemstatement.controller.LeadController;
import com.mf.ps.problemstatement.model.Lead;
import com.mf.ps.problemstatement.repository.LeadRepository;
import com.mf.ps.problemstatement.service.LeadService;

@SpringBootTest
public class LeadControllerTest {


	@Mock
    private LeadRepository leadRepository;
	
    @Mock
    private LeadService leadService;

    @InjectMocks
    private LeadController leadController;

    @Test
    void testCreateLead_Success() {
        Lead lead = new Lead();
        when(leadService.isLeadIdExists((int) anyLong())).thenReturn(false);
        when(leadRepository.save(any(Lead.class))).thenReturn(lead);

        ResponseEntity<?> response = leadController.createLead(lead, mock(BindingResult.class));

        verify(leadService, times(1)).isLeadIdExists((int) anyLong());
        verify(leadRepository, times(1)).save(any(Lead.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testCreateLead_ValidationErrors() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> response = leadController.createLead(new Lead(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testCreateLead_LeadAlreadyExists() {
        Lead lead = new Lead();
        when(leadService.isLeadIdExists((int) anyLong())).thenReturn(true);

        ResponseEntity<?> response = leadController.createLead(lead, mock(BindingResult.class));

        verify(leadService, times(1)).isLeadIdExists((int) anyLong());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testGetAllLeads() {
        List<Lead> leads = Arrays.asList(new Lead(), new Lead());
        when(leadRepository.findAll()).thenReturn(leads);

        ResponseEntity<List<Lead>> response = leadController.getAllLeads();

        verify(leadRepository, times(1)).findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(leads, response.getBody());
    }

    @Test
    void testGetLeadsByMobileNumber_NoLeadsFound() {
        String mobileNumber = "1234567890";
        when(leadService.getLeadByMobileNumber(mobileNumber)).thenReturn(Arrays.asList());

        ResponseEntity<Object> response = leadController.getLeadsByMobileNumber(mobileNumber);

        verify(leadService, times(1)).getLeadByMobileNumber(mobileNumber);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No leads found with mobile number: " + mobileNumber, response.getBody());
    }

    @Test
    void testGetLeadsByMobileNumber_LeadsFound() {
        String mobileNumber = "1234567890";
        List<Lead> leads = Arrays.asList(new Lead(), new Lead());
        when(leadService.getLeadByMobileNumber(mobileNumber)).thenReturn(leads);

        ResponseEntity<Object> response = leadController.getLeadsByMobileNumber(mobileNumber);

        verify(leadService, times(1)).getLeadByMobileNumber(mobileNumber);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(leads, response.getBody());
    }
}

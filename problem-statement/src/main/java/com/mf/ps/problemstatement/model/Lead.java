package com.mf.ps.problemstatement.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "lead")
public class Lead {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull(message = "leadId is mandatory")
	private Integer leadId;
	
	@NotBlank(message = "First name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name should contain only alphabets with no spaces")
	private String firstName;
	
	@Nullable
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String middleName;
	
	@NotBlank(message = "Last name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should contain only alphabets with no spaces")
	private String lastName;
	
	@NotBlank(message = "Mobile number is mandatory")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number format")
	private String mobileNumber;
	
	@NotNull
    @Pattern(regexp = "^(MALE|FEMALE|OTHER|Male|Female|Other|male|female|other)$", message = "Invalid gender. Please choose a valid gender.")
	private String gender;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull @Past
	private Date dateOfBirth;
	
	@NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
	private String email;
	
	public Lead() {}

	public Lead(@NotNull(message = "leadId is mandatory") Integer leadId,
			@NotBlank(message = "First name is mandatory") @Pattern(regexp = "^[a-zA-Z]+$", message = "First name should contain only alphabets with no spaces") String firstName,
			@Pattern(regexp = "^[a-zA-Z]+$", message = "Middle name should contain only alphabets with no spaces") String middleName,
			@NotBlank(message = "Last name is mandatory") @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should contain only alphabets with no spaces") String lastName,
			@NotBlank(message = "Mobile number is mandatory") @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number format") String mobileNumber,
			@NotNull(message = "Gender is mandatory") @Pattern(regexp = "^(Male|Female|Other)$", message = "Invalid gender value. Allowed values are 'Male', 'Female', 'Other'.") String gender,
			@Past(message = "Enter valid date.") Date dateOfBirth,
			@NotBlank(message = "Email is mandatory") @Email(message = "Invalid email format") String email) {
		super();
		this.leadId = leadId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
	}

	public Integer getLeadId() {
		return leadId;
	}

	public void setLeadId(Integer leadId) {
		this.leadId = leadId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

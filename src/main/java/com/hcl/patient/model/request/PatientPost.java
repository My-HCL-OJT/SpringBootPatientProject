package com.hcl.patient.model.request;

import com.hcl.patient.model.Address;
import com.hcl.patient.model.PatientMedicalHistory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*RequestBody Model for POST

{
name
phoneNumber
dateOfBirth
nationalId

addressVo{
street
city
state
landmark
pincode
}

illnessVo{
illness
description
hospitalAdmission
}*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientPost {
	private String name;
	private String phoneNumber;
	private String dob;
	private Integer nationalId;
	private Address address;
	private PatientMedicalHistory pmh;
}

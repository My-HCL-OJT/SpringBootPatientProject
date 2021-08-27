package com.hcl.patient.model.request;

import com.hcl.patient.model.entity.Address;
import com.hcl.patient.model.entity.PatientMedicalHistory;

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
zip
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

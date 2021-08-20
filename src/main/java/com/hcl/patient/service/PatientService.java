package com.hcl.patient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.patient.model.Address;
import com.hcl.patient.model.Patient;
import com.hcl.patient.model.request.PatientPost;
import com.hcl.patient.repository.PatientRepository;

@Service
public class PatientService {
	@Autowired
	private PatientRepository pr;

	public Patient createPatient(PatientPost pp) {
		// String street, String city, String state, String landmark, Integer zip
		Address add = new Address("100 Spring Peak Ct", "Holly Springs", "NC", "By the tower", 27540);
		// Patient(String name, Address address, String phoneNumber, String dob, Integer
		// nationalId) {
		Patient p = new Patient();
		p.setName(pp.getName());
		p.setAddress(pp.getAddress());
		p.setPhoneNumber(pp.getPhoneNumber());
		p.setDob(pp.getDob());
		p.setNationalId(pp.getNationalId());

		return pr.save(p);

	}
}

package com.hcl.patient.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.patient.model.Address;
import com.hcl.patient.model.Patient;
import com.hcl.patient.model.request.PatientPost;
import com.hcl.patient.repository.PatientRepository;

/*Create,Read,Update,Delete*/
@Service
public class PatientService {
	private PatientRepository pr;

	@Autowired
	public PatientService(PatientRepository pr) {
		this.pr = pr;
	}

	// Create
	public Patient createPatient(PatientPost pp) {
		// String street, String city, String state, String landmark, Integer zip
		Address add = new Address("100 Spring Peak Ct", "Holly Springs", "NC", "By the tower", 27540);
		// Patient(String name, Address address, String phoneNumber, String dob, Integer
		// nationalId) {
		Patient p = new Patient();
		p.setName(pp.getName());
		System.out.println(pp.getAddress());
		p.setAddress(pp.getAddress());
		p.setPhoneNumber(pp.getPhoneNumber());
		p.setDob(pp.getDob());
		p.setNationalId(pp.getNationalId());
		return pr.save(p);
	}

	// Read by id
	public Optional<Patient> getPatientById(Long id) {
		return pr.findById(id);
	}

	// ReadAll
	public List<Patient> getAllPatient() {
		return pr.findAll();
	}

	// Delete
	public void deletePatient(Long id) {
		pr.deleteById(id);
	}

}

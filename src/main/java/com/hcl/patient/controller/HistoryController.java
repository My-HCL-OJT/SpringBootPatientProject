package com.hcl.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.patient.model.entity.Patient;
import com.hcl.patient.model.entity.PatientMedicalHistory;
import com.hcl.patient.model.request.MedicalHistoryRequest;
import com.hcl.patient.repositories.PatientMedicalHistoryRepository;
import com.hcl.patient.repositories.PatientRepository;
import com.hcl.patient.service.PatientService;

@RestController
@RequestMapping(value = "/history")
public class HistoryController {
	PatientMedicalHistoryRepository pmhr;
	PatientService ps;
	PatientRepository pr;

	@Autowired
	public HistoryController(PatientMedicalHistoryRepository pmhr, PatientService ps, PatientRepository pr) {
		this.pmhr = pmhr;
		this.ps = ps;
		this.pr = pr;
	}

	// get the patient history from patientId
	@GetMapping(value = "/all/{id}")
	public ResponseEntity<PatientMedicalHistory> getHistoryFromId(@PathVariable Long id) {
		ResponseEntity<PatientMedicalHistory> re = new ResponseEntity<PatientMedicalHistory>(
				pmhr.findById(id).orElse(null), HttpStatus.OK);
		return re;
	}

	@PostMapping(value = "/add/{id}")
	public ResponseEntity<Patient> addPatientHistoryById(@PathVariable Long id,
			@RequestBody MedicalHistoryRequest temp) {
		ResponseEntity<Patient> re = new ResponseEntity<Patient>(ps.addHistory(id, temp), HttpStatus.OK);
		return re;
	}

	// Get all patients who are dead
	@GetMapping(value = "/{isAlive}")
	public ResponseEntity<List<Patient>> getPatientHistoryIfNoAlive(@PathVariable Boolean isAlive) {
		ResponseEntity<List<Patient>> re = new ResponseEntity<List<Patient>>(ps.getPatientIfAliveisFalse(isAlive),
				HttpStatus.OK);
		return re;
	}

	// Get all patients with Illness-Covid in last 30 days
	@GetMapping(value = "/30days/{data}")
	public ResponseEntity<List<Patient>> getPatientHistory(@PathVariable String data) {
		ResponseEntity<List<Patient>> re = new ResponseEntity<List<Patient>>(ps.getPatientByRecentDate(data),
				HttpStatus.OK);
		return re;
	}

	// Get the percentage of covid patients
	@GetMapping(value = "/percentage/{data}")
	public ResponseEntity<Double> getCovidPercentage(@PathVariable String data) {
		ResponseEntity<Double> re = new ResponseEntity<Double>(ps.getCovidPercentage(data), HttpStatus.OK);
		return re;
	}

	// Get the percentage of covid patients
	@GetMapping(value = "/Charges30")
	public ResponseEntity<Double> totalCharges() {
		ResponseEntity<Double> re = new ResponseEntity<Double>(ps.totalCharges(), HttpStatus.OK);
		return re;
	}

	// Get all patients who are currently hospitalized.
	@GetMapping(value = "/Hospitalized/{data}")
	public ResponseEntity<List<Patient>> patientHospitalized(@PathVariable Boolean data) {
		ResponseEntity<List<Patient>> re = new ResponseEntity<List<Patient>>(ps.findCurrentHospitalized(data),
				HttpStatus.OK);
		return re;
	}

	// Get all patients who have been hospitalized.
	@GetMapping(value = "/BeenHospitalized/{data}")
	public ResponseEntity<List<Patient>> patientBeenHospitalized(@PathVariable Boolean data) {
		ResponseEntity<List<Patient>> re = new ResponseEntity<List<Patient>>(ps.findIFBeenHospitalized(data),
				HttpStatus.OK);
		return re;
	}
}

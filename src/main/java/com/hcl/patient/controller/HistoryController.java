package com.hcl.patient.controller;

import java.time.LocalDate;
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

import com.hcl.patient.model.Patient;
import com.hcl.patient.model.PatientMedicalHistory;
import com.hcl.patient.model.request.MedicalHistoryRequest;
import com.hcl.patient.repository.PatientMedicalHistoryRepository;
import com.hcl.patient.repository.PatientRepository;
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
	// Get all patients who have been hospitalized.

	// Get all patients who are currently hospitalized.

	// Get all patients who are dead
	@GetMapping(value = "/{isAlive}")
	public ResponseEntity<List<Patient>> getPatientHistory(@PathVariable Boolean isAlive) {
		ResponseEntity<List<Patient>> re = new ResponseEntity<List<Patient>>(ps.getPatientIfAliveisFalse(isAlive),
				HttpStatus.OK);
		return re;
	}

	// Get all patients with Illness-Covid in last 30 days
	@GetMapping(value = "/30days")
	public ResponseEntity<List<PatientMedicalHistory>> getPatientHistory() {
		ResponseEntity<List<PatientMedicalHistory>> re = new ResponseEntity<List<PatientMedicalHistory>>(
				ps.getPatientByRecentDate(), HttpStatus.OK);
		return re;
	}

	// Get the percentage of covid patients
	@GetMapping(value = "/Cper")
	public ResponseEntity<Double> getCovidPercentage() {
		ResponseEntity<Double> re = new ResponseEntity<Double>(ps.getCovidPercentage(), HttpStatus.OK);
		return re;
	}

	// Get the percentage of covid patients
	@GetMapping(value = "/Charges30")
	public ResponseEntity<Double> totalCharges() {
		ResponseEntity<Double> re = new ResponseEntity<Double>(ps.totalCharges(), HttpStatus.OK);
		return re;
	}
	
	@GetMapping(value = "/Hospitalized")
	public ResponseEntity<List<Patient>> patientHospitalized() {
		ResponseEntity<List<Patient>> re = new ResponseEntity<List<Patient>>(ps.temp(), HttpStatus.OK);
		return re;
	}
	@GetMapping(value = "/BeenHospitalized")
	public ResponseEntity<List<Patient>> patientBeenHospitalized() {
		ResponseEntity<List<Patient>> re = new ResponseEntity<List<Patient>>(ps.temp2(), HttpStatus.OK);
		return re;
	}
}

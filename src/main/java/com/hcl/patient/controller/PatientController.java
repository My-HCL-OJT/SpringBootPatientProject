package com.hcl.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.patient.model.entity.Patient;
import com.hcl.patient.model.request.PatientPost;
import com.hcl.patient.model.request.PatientPut;
import com.hcl.patient.repositories.PatientRepository;
import com.hcl.patient.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	PatientService ps;
	PatientRepository pr;

	@Autowired
	public PatientController(PatientService ps, PatientRepository pr) {
		this.ps = ps;
		this.pr = pr;
	}

	@GetMapping
	public ResponseEntity<String> helloWorld() {
		ResponseEntity<String> re = new ResponseEntity<String>("Hello World I am a new Patient!", HttpStatus.OK);
		return re;
	}

	@PostMapping
	public ResponseEntity<Patient> postPatient(@RequestBody PatientPost pp) {
		ResponseEntity<Patient> re = new ResponseEntity<Patient>(ps.createPatient(pp), HttpStatus.CREATED);
		return re;
	}

	/*
	 * @PostMapping(value = "all") public ResponseEntity<List<Patient>>
	 * postAllPatient(@RequestBody List<PatientPost> pp) {
	 * ResponseEntity<List<Patient>> re = new
	 * ResponseEntity<List<Patient>>(ps.createListOfPatient(pp),
	 * HttpStatus.CREATED); return re; }
	 */

	@GetMapping(value = "{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
		ResponseEntity<Patient> re = new ResponseEntity<Patient>(ps.getPatientById(id).orElse(null), HttpStatus.OK);
		return re;
	}

	@GetMapping(value = "all")
	public ResponseEntity<List<Patient>> getAllPatient() {
		ResponseEntity<List<Patient>> re = new ResponseEntity<List<Patient>>(ps.getAllPatient(), HttpStatus.OK);
		return re;
	}

	// Delete- by patient Id
	@DeleteMapping(value = "{id}")
	public String deletePatient(@PathVariable Long id) {
		ps.deletePatient(id);
		return "Patient deleted with id " + id;
	}

	// get- to chk if patient exists- nationalId
	@GetMapping(value = "/ni/{id}")
	public ResponseEntity<Patient> getPatientFromNi(@PathVariable Integer id) {
		ResponseEntity<Patient> re = new ResponseEntity<Patient>(ps.getByNI(id), HttpStatus.OK);
		return re;
	}

	// get- to chk if patient exists- nationalId
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Patient> updatePatientHistory(@PathVariable Long id, @RequestBody PatientPut put) {
		ResponseEntity<Patient> re = new ResponseEntity<Patient>(ps.updatePatientHistory(id, put), HttpStatus.OK);
		return re;
	}

}

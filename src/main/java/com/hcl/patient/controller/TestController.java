package com.hcl.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.patient.model.Patient;
import com.hcl.patient.model.request.PatientPost;
import com.hcl.patient.repository.PatientRepository;
import com.hcl.patient.service.PatientService;

@RestController
@RequestMapping("/")
public class TestController {
	@Autowired
	PatientService ps;
	@Autowired
	PatientRepository pr;
	@GetMapping
	public ResponseEntity<String> helloWorld() {
		ResponseEntity<String> re = new ResponseEntity<String>("Hello World", HttpStatus.OK);
		return re;
	}
	@PostMapping
	public ResponseEntity<Patient> postPatient(@RequestBody PatientPost pp){
		ResponseEntity<Patient> re = new ResponseEntity<Patient>(ps.createPatient(pp),HttpStatus.CREATED);
		return re;
	}
	@GetMapping(value="{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Long id){
		ResponseEntity<Patient> re = new ResponseEntity<Patient>(ps.getPatientById(id).orElse(null),HttpStatus.OK);
		return re;
	}
	@GetMapping(value="all")
	public ResponseEntity<List<Patient>> getAllPatient(){
		ResponseEntity<List<Patient>> re = new ResponseEntity<List<Patient>>(ps.getAllPatient(),HttpStatus.OK);
		return re;
	}
	@DeleteMapping(value="{id}")
	public String deletePatient(@PathVariable Long id){
		ps.deletePatient(id);
		return "Patient deleted with id" + id;
	}
}

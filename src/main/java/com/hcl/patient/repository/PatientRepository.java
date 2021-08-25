package com.hcl.patient.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.patient.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	Patient findByName(String name);
	List<Patient> findByNationalId(Integer nationalId);
}

package com.hcl.patient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.patient.model.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	Patient findByName(String name);
	Patient findByNationalId(Integer nationalId);
}

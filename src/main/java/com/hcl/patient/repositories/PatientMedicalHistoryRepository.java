package com.hcl.patient.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.patient.model.entity.Patient;
import com.hcl.patient.model.entity.PatientMedicalHistory;

@Repository
public interface PatientMedicalHistoryRepository extends JpaRepository<PatientMedicalHistory, Long> {
	@Query(value = "select * from pmh where patient_id = ?1", nativeQuery = true)
	PatientMedicalHistory findByPatientId(Long Id);

	@Query(value = "select pmh.PATIENT_ID from pmh where pmh.is_Alive = :temp", nativeQuery = true)
	List<BigInteger> findByIS_AliveFalse(Boolean temp);

	@Query(value = "select pmh.PATIENT_ID from pmh where pmh.creation_date >= CURRENT_TIMESTAMP -30 and pmh.Name = :data ", nativeQuery = true)
	List<BigInteger> findByCreation_Date(String data);
	
	@Query(value = "select pmh.PATIENT_ID from pmh where pmh.Name = :data ", nativeQuery = true)
	List<BigInteger> findByNameIsCovid(String data);
	
	@Query(value = "select pmh.charges from pmh where pmh.creation_date >= CURRENT_TIMESTAMP -30", nativeQuery = true)
	List<Double> findByTotalCharges();
	
	@Query(value = "select pmh.PATIENT_ID  from pmh where pmh.HOSPITAL_ADMISSION = :temp and pmh.DISCHARGE_DATE is null", nativeQuery = true)
	List<BigInteger> findCurrentHospitalized(Boolean temp);
	
	@Query(value = "select pmh.PATIENT_ID  from pmh where pmh.HOSPITAL_ADMISSION = :temp and pmh.DISCHARGE_DATE is not null", nativeQuery = true)
	List<BigInteger> findBeenHospitalized(Boolean temp);
}

package com.hcl.patient.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.patient.model.PatientMedicalHistory;

@Repository
public interface PatientMedicalHistoryRepository extends JpaRepository<PatientMedicalHistory, Long> {
	@Query(value = "select * from pmh where patient_id = ?1", nativeQuery = true)
	PatientMedicalHistory findByPatientId(Long Id);

	@Query(value = "select pmh.PATIENT_ID from pmh where pmh.is_Alive = ?1", nativeQuery = true)
	List<BigInteger> findByIS_AliveFalse(Boolean temp);

	@Query(value = "select * from pmh where pmh.creation_date >= CURRENT_TIMESTAMP -30 and pmh.Name = 'COVID19' ", nativeQuery = true)
	List<PatientMedicalHistory> findByCreation_Date();
	
	@Query(value = "select pmh.PATIENT_ID from pmh where pmh.Name = 'COVID19' ", nativeQuery = true)
	List<BigInteger> findByNameIsCovid();
	
	@Query(value = "select pmh.charges from pmh where pmh.creation_date >= CURRENT_TIMESTAMP -30", nativeQuery = true)
	List<Double> findByTotalCharges();
	
	@Query(value = "select pmh.PATIENT_ID  from pmh where pmh.HOSPITAL_ADMISSION = true and pmh.DISCHARGE_DATE is null", nativeQuery = true)
	List<BigInteger> findCurrentHospitalized();
	
	@Query(value = "select pmh.PATIENT_ID  from pmh where pmh.HOSPITAL_ADMISSION = true and pmh.DISCHARGE_DATE is not null", nativeQuery = true)
	List<BigInteger> findIfCurrentHospitalized();
}

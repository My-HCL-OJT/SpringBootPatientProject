package com.hcl.patient.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.patient.model.IllnessEnums;
import com.hcl.patient.model.Patient;
import com.hcl.patient.model.PatientMedicalHistory;
import com.hcl.patient.model.request.MedicalHistoryRequest;
import com.hcl.patient.model.request.PatientPost;
import com.hcl.patient.model.request.PatientPut;
import com.hcl.patient.repository.PatientMedicalHistoryRepository;
import com.hcl.patient.repository.PatientRepository;

import lombok.extern.slf4j.Slf4j;

/*Create,Read,Update,Delete*/
@Slf4j
@Service
public class PatientService {
	private IllnessEnums ie;
	@Autowired
	private PatientRepository pr;
	@Autowired
	private PatientMedicalHistoryRepository pmhr;

	// Create
	public Patient createPatient(PatientPost pp) {
		Patient p = new Patient(pp.getName(), pp.getAddress(), pp.getPhoneNumber(), pp.getDob(), pp.getNationalId(),
				pp.getPmh());
		return pr.save(p);

	}

	// Read by id
	public Patient getPatientById(Long id) {
		return pr.findById(id).orElse(null);
	}

	// ReadAll
	public List<Patient> getAllPatient() {
		return pr.findAll();
	}

	// Delete
	public void deletePatient(Long id) {
		pr.deleteById(id);
	}

	// get by nationalId
	public List<Patient> getByNI(Integer ni) {
		return pr.findByNationalId(ni);
	}
	
	public Patient updatePatientHistory(Long id, PatientPut put) {
		Patient patient = pr.findById(id).orElse(null);
		List<PatientMedicalHistory> pmh = patient.getPmh();
		PatientMedicalHistory idPmh = pmh.get(put.getId()-1);
		idPmh.setName(put.getPmh().getName());
		idPmh.setDescription(put.getPmh().getDescription());
		idPmh.setHospitalAdmission(put.getPmh().getHospitalAdmission());
		idPmh.setCreationDate(put.getPmh().getCreationDate());
		idPmh.setDischargeDate(put.getPmh().getDischargeDate());
		idPmh.setIsAlive(put.getPmh().getIsAlive());
		idPmh.setCharges(put.getPmh().getCharges());
		pmhr.save(idPmh);
		pr.save(patient);
		return patient;
	}
	public Patient addHistory(Long id, MedicalHistoryRequest pmh) {
		Patient p = pr.findById(id).orElse(null);
		PatientMedicalHistory temp = new PatientMedicalHistory(pmh.getName(), pmh.getDescription(),
				pmh.getHospitalAdmission(), LocalDate.parse(pmh.getCreationDate()),
				LocalDate.parse(pmh.getDischargeDate()), pmh.getIsAlive(), pmh.getCharges());
		p.addPmh(temp);

		return pr.save(p);
	}

	public List<Patient> getPatientIfAliveisFalse(Boolean isAlive) {
		List<BigInteger> temp = pmhr.findByIS_AliveFalse(isAlive);
		List<Patient> pTemp = temp.stream().map(i -> pr.findById(i.longValue()).orElse(null))
				.collect(Collectors.toList());
		return pTemp;
	}

	public List<PatientMedicalHistory> getPatientByRecentDate() {
		List<PatientMedicalHistory> temp = pmhr.findByCreation_Date();
		return temp;
	}

	public Double getCovidPercentage() {
		List<BigInteger> temp = pmhr.findByNameIsCovid();
		List<Patient> pTemp = temp.stream().map(i -> pr.findById(i.longValue()).orElse(null))
				.collect(Collectors.toList());
		List<Patient> fTemp = pr.findAll();
		Double total = (1 - ((((double) (fTemp.size() - pTemp.size())) / fTemp.size()))) * 100;
		System.out.println(total);
		return total;
	}

	public Double totalCharges() {
		return pmhr.findByTotalCharges().stream().mapToDouble(Double::valueOf).sum();
	}
	
	public List<Patient> temp(){
		List<BigInteger> temp = pmhr.findCurrentHospitalized();
		List<Patient> pTemp = temp.stream().map(i -> pr.findById(i.longValue()).orElse(null))
				.collect(Collectors.toList());
		return pTemp;

	}

	//findIfCurrentHospitalized
	
	public List<Patient> temp2(){
		List<BigInteger> temp = pmhr.findIfCurrentHospitalized();
		List<Patient> pTemp = temp.stream().map(i -> pr.findById(i.longValue()).orElse(null))
				.collect(Collectors.toList());
		return pTemp;

	}
}

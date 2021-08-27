package com.hcl.patient.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.patient.exception.NameAlreadyExistException;
import com.hcl.patient.exception.NationalIdExistException;
import com.hcl.patient.model.entity.Patient;
import com.hcl.patient.model.entity.PatientMedicalHistory;
import com.hcl.patient.model.enums.IllnessEnums;
import com.hcl.patient.model.request.MedicalHistoryRequest;
import com.hcl.patient.model.request.PatientPost;
import com.hcl.patient.model.request.PatientPut;
import com.hcl.patient.repositories.PatientMedicalHistoryRepository;
import com.hcl.patient.repositories.PatientRepository;

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
	public Patient createPatient(PatientPost pp) throws NameAlreadyExistException, NationalIdExistException {
		List<Patient> temp = pr.findAll();
		temp.stream().forEach(i -> {
			if (i.getName().equals(pp.getName())) {
				log.warn("Patient with " + pp.getName() + " found in the db");
				throw new NameAlreadyExistException(pp.getName() + " Already exsist in the database!");
			}
			if (i.getNationalId() == pp.getNationalId()) {
				log.warn("Patient with " + pp.getNationalId() + " found in the db");
				String message = "User with national id of " + i.getNationalId() + " Already exsist in the database!";
				throw new NationalIdExistException(message);
			}

		});
		Patient p;
		PatientMedicalHistory pmh;

		String name = pp.getPmh().getName();
		switch (name) {
		case "Covid19":
			if (pp.getPmh().getDescription() == null) {
				log.info("Creating patient with COVID-19 but missing the description");
				pmh = pp.getPmh();
				pmh.setName(IllnessEnums.COVID19.getName());
				pmh.setDescription(IllnessEnums.COVID19.getDescription());
				p = new Patient(pp.getName(), pp.getAddress(), pp.getPhoneNumber(), pp.getDob(), pp.getNationalId(),
						pmh);
				return pr.save(p);
			}
			if (pp.getPmh().getHospitalAdmission() == null) {
				log.info("Creating patient with COVID-19 but missing the hospital admission");
				pmh = pp.getPmh();
				pmh.setHospitalAdmission(IllnessEnums.COVID19.getHospitalAdmission());
				p = new Patient(pp.getName(), pp.getAddress(), pp.getPhoneNumber(), pp.getDob(), pp.getNationalId(),
						pmh);
				return pr.save(p);
			}
			log.info("Creating patient with COVID-19");
			pmh = pp.getPmh();
			pmh.setName(IllnessEnums.COVID19.getName());
			p = new Patient(pp.getName(), pp.getAddress(), pp.getPhoneNumber(), pp.getDob(), pp.getNationalId(), pmh);
			return pr.save(p);
		case "COVID19Delta":
			if (pp.getPmh().getDescription() == null) {
				log.info("Creating patient with COVID-19Delta but missing the description");
				pmh = pp.getPmh();
				pmh.setName(IllnessEnums.COVID19Delta.getName());
				pmh.setDescription(IllnessEnums.COVID19Delta.getDescription());
				p = new Patient(pp.getName(), pp.getAddress(), pp.getPhoneNumber(), pp.getDob(), pp.getNationalId(),
						pmh);
				return pr.save(p);
			}
			if (pp.getPmh().getHospitalAdmission() == null) {
				log.info("Creating patient with COVID-19Delta but missing the hospital admission");
				pmh = pp.getPmh();
				pmh.setHospitalAdmission(IllnessEnums.COVID19Delta.getHospitalAdmission());
				p = new Patient(pp.getName(), pp.getAddress(), pp.getPhoneNumber(), pp.getDob(), pp.getNationalId(),
						pmh);
				return pr.save(p);
			}
			log.info("Creating patient with COVID-19Delta");
			pmh = pp.getPmh();
			pmh.setName(IllnessEnums.COVID19Delta.getName());
			p = new Patient(pp.getName(), pp.getAddress(), pp.getPhoneNumber(), pp.getDob(), pp.getNationalId(), pmh);
			return pr.save(p);
		case "COVID19Variant":
			if (pp.getPmh().getDescription() == null) {
				log.info("Creating patient with COVID-19Variant but missing the description");
				pmh = pp.getPmh();
				pmh.setName(IllnessEnums.COVID19Variant.getName());
				pmh.setDescription(IllnessEnums.COVID19Variant.getDescription());
				p = new Patient(pp.getName(), pp.getAddress(), pp.getPhoneNumber(), pp.getDob(), pp.getNationalId(),
						pmh);
				return pr.save(p);
			}
			if (pp.getPmh().getHospitalAdmission() == null) {
				log.info("Creating patient with COVID-19Variant but missing hospital admission");
				pmh = pp.getPmh();
				pmh.setHospitalAdmission(IllnessEnums.COVID19Variant.getHospitalAdmission());
				p = new Patient(pp.getName(), pp.getAddress(), pp.getPhoneNumber(), pp.getDob(), pp.getNationalId(),
						pmh);
				return pr.save(p);
			}
			log.info("Creating patient with COVID-19Variant");
			pmh = pp.getPmh();
			pmh.setName(IllnessEnums.COVID19Variant.getName());
			p = new Patient(pp.getName(), pp.getAddress(), pp.getPhoneNumber(), pp.getDob(), pp.getNationalId(), pmh);
			return pr.save(p);
		default:
			log.info("Creating patient with custom illness");
			p = new Patient(pp.getName(), pp.getAddress(), pp.getPhoneNumber(), pp.getDob(), pp.getNationalId(),
					pp.getPmh());
			return pr.save(p);
		}
	}

	// Read by id
	public Optional<Patient> getPatientById(Long id) {
		log.info("Getting Patient with " + id);
		return pr.findById(id);
	}

	// ReadAll
	public List<Patient> getAllPatient() {
		log.info("Getting all patients.");
		return pr.findAll();
	}

	// Delete
	public String deletePatient(Long id) {
		log.info("Deleting patient with " + id);
		pr.deleteById(id);
		return "SUCCESS";
	}

	// get by nationalId
	public Patient getByNI(Integer ni) {
		log.info("Getting patient with national id " + ni);
		return pr.findByNationalId(ni);
	}

	public Patient updatePatientHistory(Long id, PatientPut put) {
		log.info("Updating patient with id " + id);
		Patient patient = pr.findById(id).orElse(null);
		List<PatientMedicalHistory> pmh = patient.getPmh();
		PatientMedicalHistory idPmh = pmh.get((int) (put.getId() - 1));
		idPmh.setPatientMedicalHistory(put.getPmh().getName(), put.getPmh().getDescription(),
				put.getPmh().getHospitalAdmission(), put.getPmh().getCreationDate(), put.getPmh().getDischargeDate(),
				put.getPmh().getIsAlive(), put.getPmh().getCharges());
		pmhr.save(idPmh);
		pr.save(patient);
		return patient;
	}

	public Patient addHistory(Long id, MedicalHistoryRequest pmh) {
		log.info("Adding history to the patient with id " + 1);
		Patient p = pr.findById(id).orElse(null);
		PatientMedicalHistory temp = new PatientMedicalHistory(pmh.getName(), pmh.getDescription(),
				pmh.getHospitalAdmission(), pmh.getCreationDate(), pmh.getDischargeDate(), pmh.getIsAlive(),
				pmh.getCharges());
		p.addPmh(temp);

		return pr.save(p);
	}

	public List<Patient> getPatientIfAliveisFalse(Boolean isAlive) {
		log.info("getting dead patients");
		List<BigInteger> temp = pmhr.findByIS_AliveFalse(isAlive);
		List<Patient> pTemp = temp.stream().map(i -> pr.findById(i.longValue()).orElse(null))
				.collect(Collectors.toList());
		return pTemp;
	}

	public List<Patient> getPatientByRecentDate(String data) {
		log.info("getting patients between now and 30 days in past");
		List<BigInteger> temp = pmhr.findByCreation_Date(data);
		List<Patient> pTemp = temp.stream().map(i -> pr.findById(i.longValue()).orElse(null))
				.collect(Collectors.toList());
		return pTemp;
	}

	public Double getCovidPercentage(String data) {
		log.info("getting covid case percentage over the 30 days span");
		List<BigInteger> temp = pmhr.findByNameIsCovid(data);
		List<Patient> pTemp = temp.stream().map(i -> pr.findById(i.longValue()).orElse(null))
				.collect(Collectors.toList());
		List<Patient> fTemp = pr.findAll();
		Double total = (1 - ((((double) (fTemp.size() - pTemp.size())) / fTemp.size()))) * 100;
		return total;
	}

	public Double totalCharges() {
		log.info("getting total charges of patients over 30 day span");
		Double sum = pmhr.findByTotalCharges().stream().mapToDouble(Double::valueOf).sum();
		return sum;
	}

	public List<Patient> findCurrentHospitalized(Boolean data) {
		log.info("getting patients who are currently in hospital");
		List<BigInteger> temp = pmhr.findCurrentHospitalized(data);
		List<Patient> pTemp = temp.stream().map(i -> pr.findById(i.longValue()).orElse(null))
				.collect(Collectors.toList());
		return pTemp;
	}

	// findIfCurrentHospitalized
	public List<Patient> findIFBeenHospitalized(Boolean data) {
		log.info("getting patients who have been discharged");
		List<BigInteger> temp = pmhr.findBeenHospitalized(data);
		List<Patient> pTemp = temp.stream().map(i -> pr.findById(i.longValue()).orElse(null))
				.collect(Collectors.toList());
		return pTemp;

	}
}

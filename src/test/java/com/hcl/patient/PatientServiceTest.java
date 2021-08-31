package com.hcl.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import com.hcl.patient.model.entity.Address;
import com.hcl.patient.model.entity.Patient;
import com.hcl.patient.model.entity.PatientMedicalHistory;
import com.hcl.patient.model.request.MedicalHistoryRequest;
import com.hcl.patient.model.request.PatientPost;
import com.hcl.patient.model.request.PatientPut;
import com.hcl.patient.repositories.PatientMedicalHistoryRepository;
import com.hcl.patient.repositories.PatientRepository;
import com.hcl.patient.service.PatientService;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class PatientServiceTest {
	@Autowired
	PatientService ps;
	@MockBean
	PatientRepository pr;
	@MockBean
	PatientMedicalHistoryRepository pmhr;
	ArrayList<Patient> arr;
	Address address, address1;
	PatientMedicalHistory pmh, pmhWD, pmhWHA, pmh1, pmh2, pmhCDWD, pmhCDWHA;
	Patient patient, patientWD, patientWHA, patientCD, patientCV, patientCDWD, patientCDWHA, patientCVWD, patientCVWHA,
			ppatient;
	PatientPost patientPost, patientPostCD, patientPostCV, patientPostWD, patientPostWHA, patientPostCDWD,
			patientPostCDWHA, patientPostCVWD, patientPostCVWHA;

	/*
	 * Patient(String name, Address address, String phoneNumber, String dob, Integer
	 * nationalId,PatientMedicalHistory temp)
	 */
	/*
	 * PatientMedicalHistory(String name, String description, Boolean
	 * hospitalAdmission, LocalDate creationDate,LocalDate dischargeDate, Boolean
	 * isAlive, Double charges)
	 */
	/*
	 * Address(String street, String city, String state, String landmark, Integer
	 * zip)
	 */
	/*
	 * public class PatientPost {private String name;private String
	 * phoneNumber;private String dob;private Integer nationalId; private Address
	 * address; private PatientMedicalHistory pmh;}
	 */

	@BeforeEach
	public void init() {
		arr = new ArrayList<>();
		address = new Address("100 Spring Peak Ct.", "Apex", "NC", "Near Walmart", 27540);
		pmh = new PatientMedicalHistory("Covid19", "Coughing and sneezing", true, LocalDate.parse("2021-08-08"),
				LocalDate.parse("2021-08-08"), true, 100.0);
		patient = new Patient("Sahil Yadav", address, "919-939-4545", "05-18-1997", 1234, pmh);
		ppatient = new Patient("Sahil Yadav", address, "919-939-4545", "05-18-1997", 1234, pmh);

		arr.add(patient);

		patientPost = new PatientPost(patient.getName(), patient.getPhoneNumber(), patient.getDob(),
				patient.getNationalId(), patient.getAddress(), pmh);

		pmhWD = new PatientMedicalHistory("Covid19", null, true, LocalDate.parse("2021-08-08"),
				LocalDate.parse("2021-08-08"), true, 100.0);
		patientPostWD = new PatientPost(patient.getName(), patient.getPhoneNumber(), patient.getDob(),
				patient.getNationalId(), patient.getAddress(), pmhWD);
		patientWD = new Patient("Sahil Yadav", address, "919-939-4545", "05-18-1997", 1234, pmhWD);

		pmhWHA = new PatientMedicalHistory("Covid19", "Coughing and sneezing", null, LocalDate.parse("2021-08-08"),
				LocalDate.parse("2021-08-08"), true, 100.0);
		patientPostWHA = new PatientPost(patient.getName(), patient.getPhoneNumber(), patient.getDob(),
				patient.getNationalId(), patient.getAddress(), pmhWHA);
		patientWHA = new Patient("Sahil Yadav", address, "919-939-4545", "05-18-1997", 1234, pmhWHA);
	}

	@Test
	public void testCreateCOVID19Patient() throws Exception {
		when(ps.createPatient(patientPost)).thenReturn(patient);
		Patient patient1 = ps.createPatient(patientPost);
		assertEquals(patient1.getName(), "Sahil Yadav");
	}

	@Test
	public void testCreateCOVID19PatientWD() throws Exception {
		when(ps.createPatient(patientPostWD)).thenReturn(patientWD);
		Patient patient1 = ps.createPatient(patientPostWD);
		List<PatientMedicalHistory> pmh1 = patient1.getPmh();
		assertEquals(pmh1.get(0).getDescription(),
				"Deadly Virus with symptoms such as coughing, fever and many other deadly symptoms.");

	}

	@Test
	public void testCreateCOVID19PatientWHA() throws Exception {
		when(ps.createPatient(patientPostWHA)).thenReturn(patientWHA);
		Patient patient1 = ps.createPatient(patientPostWHA);
		List<PatientMedicalHistory> pmh1 = patient1.getPmh();
		assertEquals(pmh1.get(0).getHospitalAdmission(), true);
	}

	@BeforeEach
	public void DeltaInit() {

		pmh1 = new PatientMedicalHistory("COVID19Delta", "Coughing and sneezing", true, LocalDate.parse("2021-08-08"),
				LocalDate.parse("2021-08-08"), true, 100.0);
		patientCD = new Patient("Sahil Yadav", address, "919-939-4545", "05-18-1997", 1234, pmh1);
		patientPostCD = new PatientPost(patientCD.getName(), patientCD.getPhoneNumber(), patientCD.getDob(),
				patientCD.getNationalId(), patientCD.getAddress(), pmh1);

		pmhCDWD = new PatientMedicalHistory("COVID19Delta", null, true, LocalDate.parse("2021-08-08"),
				LocalDate.parse("2021-08-08"), true, 100.0);
		patientPostCDWD = new PatientPost(patient.getName(), patient.getPhoneNumber(), patient.getDob(),
				patient.getNationalId(), patient.getAddress(), pmhCDWD);
		patientCDWD = new Patient("Sahil Yadav", address, "919-939-4545", "05-18-1997", 1234, pmhCDWD);

		pmhCDWHA = new PatientMedicalHistory("COVID19Delta", "Coughing and sneezing", null,
				LocalDate.parse("2021-08-08"), LocalDate.parse("2021-08-08"), true, 100.0);
		patientPostCDWHA = new PatientPost(patient.getName(), patient.getPhoneNumber(), patient.getDob(),
				patient.getNationalId(), patient.getAddress(), pmhCDWHA);
		patientCDWHA = new Patient("Sahil Yadav", address, "919-939-4545", "05-18-1997", 1234, pmhCDWHA);
	}

	@Test
	public void testCreateCOVID19DeltaPatient() throws Exception {
		when(ps.createPatient(patientPostCD)).thenReturn(patientCD);
		Patient patient1 = ps.createPatient(patientPostCD);
		assertEquals(patient1.getName(), "Sahil Yadav");
	}

	@Test
	public void testCreateCOVID19DeltaPatientWD() throws Exception {
		when(ps.createPatient(patientPostCDWD)).thenReturn(patientCDWD);
		Patient patient1 = ps.createPatient(patientPostCDWD);
		List<PatientMedicalHistory> pmh1 = patient1.getPmh();
		assertEquals(pmh1.get(0).getName(), "COVID-19 Delta Strain");

	}

	@Test
	public void testCreateCOVID19DeltaPatientWHA() throws Exception {
		when(ps.createPatient(patientPostCDWHA)).thenReturn(patientCDWHA);
		Patient patient1 = ps.createPatient(patientPostCDWHA);
		List<PatientMedicalHistory> pmh1 = patient1.getPmh();
		assertEquals(pmh1.get(0).getHospitalAdmission(), true);
	}

	@BeforeEach
	public void VariantInit() {
		pmh2 = new PatientMedicalHistory("COVID19Variant", "Coughing and sneezing", true, LocalDate.parse("2021-08-08"),
				LocalDate.parse("2021-08-08"), true, 100.0);
		patientCV = new Patient("Sahil Yadav", address, "919-939-4545", "05-18-1997", 1234, pmh2);
		patientPostCV = new PatientPost(patientCV.getName(), patientCV.getPhoneNumber(), patientCV.getDob(),
				patientCV.getNationalId(), patientCV.getAddress(), pmh2);

		pmhCDWD = new PatientMedicalHistory("COVID19Variant", null, true, LocalDate.parse("2021-08-08"),
				LocalDate.parse("2021-08-08"), true, 100.0);
		patientCVWD = new Patient("Sahil Yadav", address, "919-939-4545", "05-18-1997", 1234, pmhCDWD);
		patientPostCVWD = new PatientPost(patientCVWD.getName(), patientCVWD.getPhoneNumber(), patientCVWD.getDob(),
				patientCVWD.getNationalId(), patientCVWD.getAddress(), pmhCDWD);

		pmhCDWHA = new PatientMedicalHistory("COVID19Variant", "Coughing and sneezing", null,
				LocalDate.parse("2021-08-08"), LocalDate.parse("2021-08-08"), true, 100.0);
		patientCVWHA = new Patient("Sahil Yadav", address, "919-939-4545", "05-18-1997", 1234, pmhCDWHA);
		patientPostCVWHA = new PatientPost(patientCVWHA.getName(), patientCVWHA.getPhoneNumber(), patientCVWHA.getDob(),
				patientCVWHA.getNationalId(), patientCVWHA.getAddress(), pmhCDWHA);
	}

	@Test
	public void testCreateCOVID19VariantPatient() throws Exception {
		when(ps.createPatient(patientPostCV)).thenReturn(patientCV);
		Patient patient1 = ps.createPatient(patientPostCV);
		assertEquals(patient1.getName(), "Sahil Yadav");
	}

	@Test
	public void testCreateCOVID19VariantPatientWD() throws Exception {
		when(ps.createPatient(patientPostCVWD)).thenReturn(patientCVWD);
		Patient patient1 = ps.createPatient(patientPostCVWD);
		List<PatientMedicalHistory> pmh1 = patient1.getPmh();
		assertEquals(pmh1.get(0).getName(), "COVID-19 Variant Strain");

	}

	@Test
	public void testCreateCOVID19VariantPatientWHA() throws Exception {
		when(ps.createPatient(patientPostCVWHA)).thenReturn(patientCVWHA);
		Patient patient1 = ps.createPatient(patientPostCVWHA);
		List<PatientMedicalHistory> pmh1 = patient1.getPmh();
		assertEquals(pmh1.get(0).getHospitalAdmission(), true);
	}

	@Test
	public void testGetAllPatient() throws Exception {
		when(ps.getAllPatient()).thenReturn(arr);
		List<Patient> list = ps.getAllPatient();
		assertEquals(list.size(), 1);
		assertTrue(list.get(0).getName().equals("Sahil Yadav"));
	}

	@Test
	public void testGetPatientById() throws Exception {
		when(ps.getPatientById(1l)).thenReturn(Optional.of(patient));
		Patient patient1 = ps.getPatientById(1l).orElse(null);
		assertEquals(patient1.getName(), "Sahil Yadav");
	}

	@Test
	@DisplayName("Delete Patient By Id")
	public void deletePatientById() {
		when(ps.getPatientById(1l)).thenReturn(Optional.of(patient));
		ps.deletePatient(patient.getId());
		verify(pr).deleteById(patient.getId());
	}

	@Test
	public void testGetPatientByNi() throws Exception {
		when(ps.getByNI(1234)).thenReturn(patient);
		Patient patient1 = ps.getByNI(1234);
		assertEquals(patient1.getNationalId(), 1234);
	}

	@Test
	public void testAddHistory() throws Exception {
		when(ps.getPatientById(1l)).thenReturn(Optional.of(patient));
		Patient patient1 = ps.getPatientById(1l).orElse(null);
		MedicalHistoryRequest pmhr1 = new MedicalHistoryRequest("Covid19", "Coughing and sneezing", true,
				LocalDate.parse("2021-08-08"), LocalDate.parse("2021-08-08"), false, 100.0);
		ps.addHistory(1l, pmhr1);
		List<PatientMedicalHistory> pmhL = patient.getPmh();
		assertEquals(pmhL.get(1).getIsAlive(), false);
	}

	@Test
	public void testUpdate() throws Exception {
		when(ps.getPatientById(1l)).thenReturn(Optional.of(patient));
		Patient patient1 = ps.getPatientById(1l).orElse(null);
		PatientMedicalHistory pmhr1 = new PatientMedicalHistory("Covid19", "Coughing and sneezing", true,
				LocalDate.parse("2021-08-08"), LocalDate.parse("2021-08-08"), false, 100.0);
		PatientPut pp = new PatientPut(1l, pmhr1);
		ps.updatePatientHistory(1l, pp);
		List<PatientMedicalHistory> pmhL = patient.getPmh();
		assertEquals(pmhL.get(0).getIsAlive(), false);
	}
//	@Test
//	public void testGetCovidPercentage() throws Exception{
//		when(ps.totalCharges()).thenReturn(100.0);
//		Double sum = ps.totalCharges();
//		assertEquals(sum,100.0);
//	}
	@Test
	public void testFindIfCurrentHospitalized() throws Exception {
		List<BigInteger> a = new ArrayList<>();
		a.add(BigInteger.valueOf(1l));
		when(pmhr.findBeenHospitalized(true)).thenReturn(a);
		List<BigInteger> temp = pmhr.findBeenHospitalized(true);
		assertEquals(temp.get(0),BigInteger.valueOf(1l));
	}
	@Test
	public void testException() {
		when(ps.getPatientById(1l)).thenReturn(Optional.of(patient)).thenReturn(Optional.of(patient));

	}
}

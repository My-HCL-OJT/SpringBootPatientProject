package com.hcl.patient;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.patient.controller.HistoryController;
import com.hcl.patient.model.entity.Address;
import com.hcl.patient.model.entity.Patient;
import com.hcl.patient.model.entity.PatientMedicalHistory;
import com.hcl.patient.model.request.MedicalHistoryRequest;
import com.hcl.patient.model.request.PatientPost;
import com.hcl.patient.repositories.PatientMedicalHistoryRepository;
import com.hcl.patient.repositories.PatientRepository;
import com.hcl.patient.service.PatientService;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class HistoryControllerTest {
	ObjectMapper mapper;

	@MockBean
	PatientService ps;
	@MockBean
	PatientRepository pr;
	@MockBean
	PatientMedicalHistoryRepository pmhr;
	@InjectMocks
	HistoryController historyController;

	private MockMvc mockMvc;
	ArrayList<Patient> arr;
	ArrayList<Patient> arr1;
	Address address,address1;
	PatientMedicalHistory pmh,pmh1;
	Patient patient,patient1;
	PatientPost patientPost;

	@BeforeEach
	public void contextLoads() {
		mapper = new ObjectMapper();
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(historyController).build();
	}

	@BeforeEach
	public void init() {
		arr = new ArrayList<>();
		arr1 = new ArrayList<>();
		address = new Address("100 Spring Peak Ct.", "Apex", "NC", "Near Walmart", 27540);
		pmh = new PatientMedicalHistory("Covid19", "Coughing and sneezing", true, LocalDate.parse("2021-08-08"),
				LocalDate.parse("2021-08-08"), true, 100.0);
		patient = new Patient("Sahil Yadav", address, "919-939-4545", "05-18-1997", 1234, pmh);
		
		address1 = new Address("100 Spring Peak Ct.", "Apex", "NC", "Near Walmart", 27540);
		pmh1 = new PatientMedicalHistory("Covid19", "Coughing and sneezing", true, LocalDate.parse("2021-08-08"),
				null, true, 100.0);
		patient1 = new Patient("Sahil Yadav", address1, "919-939-4545", "05-18-1997", 1234, pmh1);
		arr.add(patient);
		arr1.add(patient1);
		patientPost = new PatientPost(patient.getName(), patient.getPhoneNumber(), patient.getDob(),
				patient.getNationalId(), patient.getAddress(), pmh);
	}

//	@Test
//	void shouldAddPatientHistoryById() throws Exception {
//		MedicalHistoryRequest tem = new MedicalHistoryRequest();
//		when(ps.addHistory(1l,tem)).thenReturn(patient);
//		mockMvc.perform(post("/history/add/1").accept(MediaType.APPLICATION_JSON))
//				.andExpect(jsonPath("$.name").value("Sahil Yadav"));
//	}
	@Test
	void shouldReturnHistoryFromId() throws Exception {
		when(pmhr.findById(1l)).thenReturn(Optional.of(pmh));
		mockMvc.perform(get("/history/all/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Covid19"));
	}

	@Test
	void shouldReturnHistoryIfDead() throws Exception {
		when(ps.getPatientIfAliveisFalse(true)).thenReturn(arr);
		mockMvc.perform(get("/history/true").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Sahil Yadav"));
	}

	@Test
	void shouldReturngetPatientHistory() throws Exception {
		when(ps.getPatientByRecentDate("COVID-19")).thenReturn(arr);
		mockMvc.perform(get("/history/30days/COVID-19").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Sahil Yadav"));
	}

	@Test
	void shouldReturngetCovidPercentage() throws Exception {
		when(ps.getCovidPercentage("COVID-19")).thenReturn(pmh.getCharges());
		mockMvc.perform(get("/history/percentage/COVID-19").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void shouldReturnTotalCharges() throws Exception {
		when(ps.totalCharges()).thenReturn(pmh.getCharges());
		mockMvc.perform(get("/history/Charges30").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void shouldReturnPatientBeenHospitalized() throws Exception {
		when(ps.findIFBeenHospitalized(true)).thenReturn(arr);
		mockMvc.perform(get("/history/BeenHospitalized/true").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].name").value("Sahil Yadav"));
	}
	@Test
	void shouldReturnPatientHospitalized() throws Exception {
		when(ps.findCurrentHospitalized(true)).thenReturn(arr1);
		mockMvc.perform(get("/history/Hospitalized/true").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].name").value("Sahil Yadav"));
	}
}

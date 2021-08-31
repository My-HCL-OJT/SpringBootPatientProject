package com.hcl.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hcl.patient.controller.PatientController;
import com.hcl.patient.model.entity.Address;
import com.hcl.patient.model.entity.Patient;
import com.hcl.patient.model.entity.PatientMedicalHistory;
import com.hcl.patient.model.request.PatientPost;
import com.hcl.patient.repositories.PatientMedicalHistoryRepository;
import com.hcl.patient.repositories.PatientRepository;
import com.hcl.patient.service.PatientService;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {
	@Autowired
	ObjectMapper mapper;

	@MockBean
	PatientService ps;
	@MockBean
	PatientRepository pr;
	@MockBean
	PatientMedicalHistoryRepository pmhr;
	@InjectMocks
	PatientController patientController;

	private MockMvc mockMvc;
	ArrayList<Patient> arr;
	Address address;
	PatientMedicalHistory pmh;
	Patient patient;
	PatientPost patientPost;

	@BeforeEach
	public void contextLoads() {
		mapper = new ObjectMapper();
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
	}

	@BeforeEach
	public void init() {
		arr = new ArrayList<>();
		address = new Address("100 Spring Peak Ct.", "Apex", "NC", "Near Walmart", 27540);
		pmh = new PatientMedicalHistory("Covid19", "Coughing and sneezing", true, LocalDate.parse("2021-08-08"),
				LocalDate.parse("2021-08-08"), true, 100.0);
		patient = new Patient("Sahil Yadav", address, "919-939-4545", "05-18-1997", 1234, pmh);
//		ppatient = new Patient("Sahil Yadav", address, "919-939-4545", "05-18-1997", 1234, pmh);

		arr.add(patient);

		patientPost = new PatientPost(patient.getName(), patient.getPhoneNumber(), patient.getDob(),
				patient.getNationalId(), patient.getAddress(), pmh);

//		pmhWD = new PatientMedicalHistory("Covid19", null, true, LocalDate.parse("2021-08-08"),
//				LocalDate.parse("2021-08-08"), true, 100.0);
//		patientPostWD = new PatientPost(patient.getName(), patient.getPhoneNumber(), patient.getDob(),
//				patient.getNationalId(), patient.getAddress(), pmhWD);
//		patientWD = new Patient("Sahil Yadav", address, "919-939-4545", "05-18-1997", 1234, pmhWD);
//
//		pmhWHA = new PatientMedicalHistory("Covid19", "Coughing and sneezing", null, LocalDate.parse("2021-08-08"),
//				LocalDate.parse("2021-08-08"), true, 100.0);
//		patientPostWHA = new PatientPost(patient.getName(), patient.getPhoneNumber(), patient.getDob(),
//				patient.getNationalId(), patient.getAddress(), pmhWHA);
//		patientWHA = new Patient("Sahil Yadav", address, "919-939-4545", "05-18-1997", 1234, pmhWHA);
	}

	@Test
	void shouldReturnHelloWorld() throws Exception {
		this.mockMvc.perform(get("/patient")).andExpect(status().isOk())
				.andExpect(content().string("Hello World I am a new Patient!"));
	}

//	@Test
//	void shouldReturnPostPatient() throws Exception {
//		when(ps.createPatient(patientPost)).thenReturn(patient);
//	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//	    String requestJson=ow.writeValueAsString(patient);
//
//	    mockMvc.perform(post("/patient").contentType(APPLICATION_JSON_UTF8)
//	        .content(requestJson))
//	        .andExpect(status().isOk());		}

	@Test
	void shouldReturnIdPatient() throws Exception {
		when(ps.getPatientById(1l)).thenReturn(Optional.of(patient));
		mockMvc.perform(get("/patient/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Sahil Yadav"));
	}

	@Test
	void shouldReturnNiPatient() throws Exception {
		when(ps.getByNI(1234)).thenReturn(patient);
		mockMvc.perform(get("/patient/ni/1234").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Sahil Yadav"));
	}

	@Test
	void shouldReturnReturnAllPatient() throws Exception {
		when(ps.getAllPatient()).thenReturn(arr);
		mockMvc.perform(get("/patient/all").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Sahil Yadav"));
	}

//	@Test
//	void deletePatient() throws Exception {
//		when(ps.getPatientById(1l)).thenReturn(Optional.of(patient));
//		when(ps.deletePatient(1l)).thenReturn("SUCCESS");
//		mockMvc.perform(delete("/patient", 1L)).andExpect(status().isOk())
//				.andExpect(content().string("Patient deleted with id 1"));
//
//	}
}

package com.hcl.patient.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 *Patient

1.PatientId
2.Name
3.AddressId
4.PhoneNumber
5.dateOfBirth
6.nationalId(Aadhar number)
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "patient")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Address address;

	private String phoneNumber;

	private String dob;

	private Integer nationalId;
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	@ToString.Exclude
	private List<PatientMedicalHistory> pmh = new ArrayList<PatientMedicalHistory>();

	public void addPmh(PatientMedicalHistory data) {
		System.out.println(data);
		pmh.add(data);
	}

	public void deletePmh(PatientMedicalHistory data) {
		pmh.remove(data);

	}

	public Patient(String name, Address address, String phoneNumber, String dob, Integer nationalId,
			PatientMedicalHistory temp) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.nationalId = nationalId;
		this.pmh.add(temp);

	}

}

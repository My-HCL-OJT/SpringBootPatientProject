package com.hcl.patient.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	@Column(name = "patient_id")
	private Integer id;
	@Column(name = "patient_name")
	private String name;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Address address;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<PatientMedicalHistory> pmh;

	@Column(name = "patient_phoneNumber")
	private String phoneNumber;
	@Column(name = "patient_dob")
	private String dob;
	@Column(name = "patient_nationalId")
	private Integer nationalId;

	public boolean addPhoneNumber(PatientMedicalHistory data) {
		return this.pmh.add(data);
	}

	public boolean deletePhoneNumber(PatientMedicalHistory data) {
		return this.pmh.remove(data);

	}

	public Patient(String name, Address address, String phoneNumber, String dob, Integer nationalId) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.nationalId = nationalId;
	}

}

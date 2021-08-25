package com.hcl.patient.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*PatientMedicalHistory

1. Id
2. Illness- ENUM(Select from the available options)
3. Desription(of illness)
4. hospitalAdmission(true/false)
5. creationDate
6. dischargedate(optional)- only to be filled if hospitalAdmission is true
7. isAlive- (true/false)
8. Charges
9. patientId
*/
@Entity
@Table(name = "pmh")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientMedicalHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	private Boolean hospitalAdmission;
	@Column(columnDefinition = "DATE")
	private LocalDate creationDate;
	@Column(nullable = true, columnDefinition = "DATE")
	private LocalDate dischargeDate;

	private Boolean isAlive;

	public Boolean isAlive() {
		return this.isAlive;
	}

	private Double charges;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	// @JoinColumn(name="patient_id",referencedColumnName="id")
	private Patient patient;

	public PatientMedicalHistory(String name, String description, Boolean hospitalAdmission, LocalDate creationDate,
			LocalDate dischargeDate, Boolean isAlive, Double charges) {
		this.name = name;
		this.description = description;
		this.hospitalAdmission = hospitalAdmission;
		this.creationDate = creationDate;
		this.dischargeDate = dischargeDate;
		this.isAlive = isAlive;
		this.charges = charges;
	}
}

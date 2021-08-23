package com.hcl.patient.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
@Entity(name = "pmh")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientMedicalHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pmh_id")
	private Integer id;
	@Column(name = "pmh_enums")
	private IllnessEnums illEnums;
	@Column(name = "pmh_descp")
	private String Description;
	@Column(name = "pmh_hospitalAdmission")
	private Boolean hospitalAdmission;
	@Column(name = "pmh_cdate")
	private Date creationDate;
	@Column(name = "pmh_ddate", nullable = true)
	private Date dischargeDate;
	@Column(name = "pmh_isAlive")
	private Boolean isAlive;
	@Column(name = "pmh_charges")
	private Double charges;
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	private Patient patient;

	public PatientMedicalHistory(Date creationDate, Date dischargeDate, Boolean isAlive, Double charges) {
		this.creationDate = creationDate;
		this.dischargeDate = dischargeDate;
		this.isAlive = isAlive;
		this.charges = charges;
	}
}

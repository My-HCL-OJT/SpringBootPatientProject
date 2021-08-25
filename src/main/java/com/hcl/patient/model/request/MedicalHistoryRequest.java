package com.hcl.patient.model.request;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalHistoryRequest {
	private String name;
	private String description;
	private Boolean hospitalAdmission;
	private String creationDate;
	private String dischargeDate;
	private Boolean isAlive;
	private Double charges;
}

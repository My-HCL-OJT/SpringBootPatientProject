package com.hcl.patient.model.request;

import com.hcl.patient.model.entity.PatientMedicalHistory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * RequestBody model for PUT
 * {
 * patientId
 * illnessVo{
 * illness
 * description
 * hospitalAdmission
 * }
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientPut {
	private Long id;
	private PatientMedicalHistory pmh;
}

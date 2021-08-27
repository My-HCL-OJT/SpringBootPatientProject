package com.hcl.patient.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Illness {
	private String name;
	private String description;
	private Boolean hospitalAdmission;
}

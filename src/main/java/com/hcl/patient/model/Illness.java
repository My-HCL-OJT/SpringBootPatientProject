package com.hcl.patient.model;

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

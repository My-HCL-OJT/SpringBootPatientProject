package com.hcl.patient.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

/*illnessVo{
illness
description
hospitalAdmission
}*/
@Getter
public enum IllnessEnums {
	COVID19(1, "COVID-19", "Deadly Virus", true);

	private final Integer id;
	private String name;
	private String description;
	private Boolean hospitalAdmission;
	private static Map<Integer, Illness> idvalueMap = new HashMap<>();
	static {
		for (IllnessEnums illness : IllnessEnums.values()) {
			idvalueMap.put(illness.getId(),
					new Illness(illness.getName(), illness.getDescription(), illness.getHospitalAdmission()));
		}
	}

	IllnessEnums(final Integer id, final String name, final String description, final Boolean hospitalAdmission) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.hospitalAdmission = hospitalAdmission;
	}

	String getName() {
		return name;
	}

	String getDescription() {
		return description;
	}

	Boolean getHospitalAdmission() {
		return hospitalAdmission;
	}

}

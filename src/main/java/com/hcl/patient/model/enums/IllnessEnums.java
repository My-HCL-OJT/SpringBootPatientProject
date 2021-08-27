package com.hcl.patient.model.enums;

import java.util.HashMap;
import java.util.Map;

import com.hcl.patient.model.entity.Illness;

import lombok.Getter;

/*illnessVo{
illness
description
hospitalAdmission
}*/
@Getter
public enum IllnessEnums {
	COVID19(1, "COVID-19", "Deadly Virus with symptoms such as coughing, fever and many other deadly symptoms.", true),
	COVID19Delta(2, "COVID-19 Delta Strain",
			"A deadly version of the COVID-19 originated in Asia and is more deadly than COVID-19", true),
	COVID19Variant(3, "COVID-19 Variant Strain",
			"A deadly version of the COVID-19 originated around the world and can be more deadly than COVID-19", true);

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

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getHospitalAdmission() {
		return hospitalAdmission;
	}

}

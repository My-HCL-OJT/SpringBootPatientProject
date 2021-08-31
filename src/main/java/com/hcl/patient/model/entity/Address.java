package com.hcl.patient.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*one patient has one address*/

/*Address

1. AddressId
2. Street
3. City
4. State
5. Landmark
6. Pincode
*/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String street;

	private String city;

	private String state;

	private String landmark;

	private Integer zip;

	public Address(String street, String city, String state, String landmark, Integer zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.landmark = landmark;
		this.zip = zip;
	}
}
package com.hcl.patient.model;

import javax.persistence.Column;
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
@Entity(name="address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Long id;
	@Column(name = "address_street")
	private String street;
	@Column(name = "address_city")
	private String city;
	@Column(name = "address_state")
	private String state;
	@Column(name = "address_landmark")
	private String landmark;
	@Column(name = "address_zip")
	private Integer zip;

	public Address(String street, String city, String state, String landmark, Integer zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.landmark = landmark;
		this.zip = zip;
	}
}

package com.hcl.patient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.patient.model.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}

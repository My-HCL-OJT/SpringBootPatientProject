# SpringBootProject
Exercise

Patient

1.PatientId
2.Name
3.AddressId
4.PhoneNumber
5.dateOfBirth
6.nationalId(Aadhar number)


Address

1. AddressId
2. Street
3. City
4. State
5. Landmark
6. Pincode

PatientMedicalHistory

1. Id
2. Illness- ENUM(Select from the available options)
3. Desription(of illness)
4. hospitalAdmission(true/false)
5. creationDate
6. dischargedate(optional)- only to be filled if hospitalAdmission is true
7. isAlive- (true/false)
8. Charges
9. patientId


1. get the patient history from patientId

2. get- to chk if patient exists- nationalId

3. POST to create a new patient record (If a patient already exist- then we will throw a custom exception with a message that patient already exist- RestControllerAdvice)

4. PUT- we will update the patient illness

5. Delete- by patient Id

6. Get all patients with Illness-Covid in last 30 days

7. Get the percentage of covid patients

8. Get all patients who have been hospitalized.

9. Get all patients who are currently hospitalized.

10. Get all patients who are dead.

11.Get the total sum of patient treatment in last 30 days.



RequestBody Model for POST

{
name
phoneNumber
dateOfBirth
nationalId

addressVo{
street
city
state
landmark
pincode
}

illnessVo{
illness
description
hospitalAdmission
}


RequestBody model for PUT
{
patientId
illnessVo{
illness
description
hospitalAdmission
}

}

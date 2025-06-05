package case_study_hospital_management.entity;

import case_study_hospital_management.common.enums.BloodType;
import case_study_hospital_management.util.CSVUtil;
import case_study_hospital_management.util.DateUtil;

import java.time.LocalDate;

public class PatientEntity extends PersonEntity {
    private LocalDate dob;
    private String address;
    private String emergencyContact;
    private BloodType bloodType;
    private String allergies;
    private LocalDate registrationDate;

    public PatientEntity(String id, String fullName, Boolean gender, String phoneNumber, LocalDate dob, String address, String emergencyContact, BloodType bloodType, String allergies, LocalDate registrationDate) {
        super(id, fullName, gender, phoneNumber);
        this.dob = dob;
        this.address = address;
        this.emergencyContact = emergencyContact;
        this.bloodType = bloodType;
        this.allergies = allergies;
        this.registrationDate = registrationDate;
    }

    public PatientEntity(String fullName, Boolean gender, String phoneNumber, LocalDate dob, String address, String emergencyContact, BloodType bloodType, String allergies) {
        super(fullName, gender, phoneNumber);
        this.dob = dob;
        this.address = address;
        this.emergencyContact = emergencyContact;
        this.bloodType = bloodType;
        this.allergies = allergies;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return String.join(",",
                CSVUtil.escapeCSV(getId()),
                CSVUtil.escapeCSV(getFullName()),
                String.valueOf(getGender()),
                CSVUtil.escapeCSV(getPhoneNumber()),
                dob != null ? DateUtil.formatDate(dob) : "",
                CSVUtil.escapeCSV(address),
                CSVUtil.escapeCSV(emergencyContact),
                bloodType != null ? bloodType.getDisplayName() : "",
                CSVUtil.escapeCSV(allergies),
                registrationDate != null ? DateUtil.formatDate(registrationDate) : "",
                String.valueOf(getDeleted()));
    }
}

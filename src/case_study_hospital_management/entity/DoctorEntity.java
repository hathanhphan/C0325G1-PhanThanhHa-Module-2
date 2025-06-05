package case_study_hospital_management.entity;

import case_study_hospital_management.common.enums.DoctorSpecialization;

public class DoctorEntity extends PersonEntity {
    private DoctorSpecialization specialization;
    private String email;
    private String licenseNumber;
    private int yearOfExperience;
    private String workingHours;
    private double consultationFee;

    public DoctorEntity(String id, String fullName, Boolean gender, String phoneNumber, DoctorSpecialization specialization, String email, String licenseNumber, int yearOfExperience, String workingHours, double consultationFee) {
        super(id, fullName, gender, phoneNumber);
        this.specialization = specialization;
        this.email = email;
        this.licenseNumber = licenseNumber;
        this.yearOfExperience = yearOfExperience;
        this.workingHours = workingHours;
        this.consultationFee = consultationFee;
    }

    public DoctorSpecialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(DoctorSpecialization specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public int getYearOfExperience() {
        return yearOfExperience;
    }

    public void setYearOfExperience(int yearOfExperience) {
        this.yearOfExperience = yearOfExperience;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }
}

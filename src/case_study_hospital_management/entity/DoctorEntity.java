package case_study_hospital_management.entity;

import case_study_hospital_management.common.enums.DoctorSpecialization;
import case_study_hospital_management.util.CSVUtil;
import case_study_hospital_management.util.DateUtil;

public class DoctorEntity extends PersonEntity {
    private DoctorSpecialization specialization;
    private String email;
    private String licenseNumber;
    private Integer yearOfExperience;
    private String workingHours;
    private Double consultationFee;

    public DoctorEntity(String id, String fullName, Boolean gender, String phoneNumber, DoctorSpecialization specialization, String email, String licenseNumber, Integer yearOfExperience, String workingHours, Double consultationFee) {
        super(id, fullName, gender, phoneNumber);
        this.specialization = specialization;
        this.email = email;
        this.licenseNumber = licenseNumber;
        this.yearOfExperience = yearOfExperience;
        this.workingHours = workingHours;
        this.consultationFee = consultationFee;
    }

    public DoctorEntity(String fullName, Boolean gender, String phoneNumber, DoctorSpecialization specialization, String email, String licenseNumber, Integer yearOfExperience, String workingHours, Double consultationFee) {
        super(fullName, gender, phoneNumber);
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

    public Integer getYearOfExperience() {
        return yearOfExperience;
    }

    public void setYearOfExperience(Integer yearOfExperience) {
        this.yearOfExperience = yearOfExperience;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public Double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(Double consultationFee) {
        this.consultationFee = consultationFee;
    }

    @Override
    public String toString() {
        return String.join(",",
                CSVUtil.escapeCSV(getId()),
                CSVUtil.escapeCSV(getFullName()),
                String.valueOf(getGender()),
                CSVUtil.escapeCSV(getPhoneNumber()),
                CSVUtil.escapeCSV(specialization.getDisplayName()),
                CSVUtil.escapeCSV(email),
                CSVUtil.escapeCSV(licenseNumber),
                yearOfExperience.toString(),
                CSVUtil.escapeCSV(workingHours),
                consultationFee.toString(),
                String.valueOf(getDeleted()));
    }
}

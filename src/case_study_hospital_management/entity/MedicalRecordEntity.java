package case_study_hospital_management.entity;

import java.time.LocalDate;

public class MedicalRecordEntity {
    private String recordId;
    private String patientId;
    private String doctorId;
    private String appointmentId;
    private LocalDate visitDate;
    private String symptoms;
    private String diagnosis;
    private String treatment;
    private String prescriptions;
    private LocalDate followUpDate;
    private Double totalCost;
    private String notes;
}

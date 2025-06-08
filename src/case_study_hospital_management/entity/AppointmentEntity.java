package case_study_hospital_management.entity;

import case_study_hospital_management.common.enums.AppointmentStatus;
import case_study_hospital_management.util.CSVUtil;
import case_study_hospital_management.util.DateUtil;

import java.time.LocalDate;

public class AppointmentEntity {
    private String id;
    private String patientId;
    private String doctorId;
    private LocalDate appointmentDate;
    private String appointmentTime;
    private AppointmentStatus status;
    private String reason;
    private String notes;
    private String parentAppointmentId;
    private String newAppointmentId;
    private String rescheduleReason;
    private Boolean isDeleted = false;

    private PatientEntity patient;
    private DoctorEntity doctor;

    public AppointmentEntity(String id, String patientId, String doctorId, LocalDate appointmentDate, String appointmentTime, AppointmentStatus status, String reason, String notes, String parentAppointmentId, String newAppointmentId, String rescheduleReason) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.reason = reason;
        this.notes = notes;
        this.parentAppointmentId = parentAppointmentId;
        this.newAppointmentId = newAppointmentId;
        this.rescheduleReason = rescheduleReason;
    }

    public AppointmentEntity(String patientId, String doctorId, LocalDate appointmentDate, String appointmentTime, AppointmentStatus status, String reason, String notes, String parentAppointmentId, String newAppointmentId, String rescheduleReason) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.reason = reason;
        this.notes = notes;
        this.parentAppointmentId = parentAppointmentId;
        this.newAppointmentId = newAppointmentId;
        this.rescheduleReason = rescheduleReason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getParentAppointmentId() {
        return parentAppointmentId;
    }

    public void setParentAppointmentId(String parentAppointmentId) {
        this.parentAppointmentId = parentAppointmentId;
    }

    public String getNewAppointmentId() {
        return newAppointmentId;
    }

    public void setNewAppointmentId(String newAppointmentId) {
        this.newAppointmentId = newAppointmentId;
    }

    public String getRescheduleReason() {
        return rescheduleReason;
    }

    public void setRescheduleReason(String rescheduleReason) {
        this.rescheduleReason = rescheduleReason;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return String.join(",",
                CSVUtil.escapeCSV(id),
                CSVUtil.escapeCSV(patientId),
                CSVUtil.escapeCSV(doctorId),
                CSVUtil.escapeCSV(DateUtil.formatDate(appointmentDate)),
                CSVUtil.escapeCSV(appointmentTime),
                CSVUtil.escapeCSV(status.getCode()),
                CSVUtil.escapeCSV(reason),
                CSVUtil.escapeCSV(notes),
                CSVUtil.escapeCSV(parentAppointmentId),
                CSVUtil.escapeCSV(newAppointmentId),
                CSVUtil.escapeCSV(rescheduleReason),
                String.valueOf(getDeleted()));
    }
}

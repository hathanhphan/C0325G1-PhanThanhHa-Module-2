package case_study_hospital_management.repository;

import case_study_hospital_management.common.enums.AppointmentStatus;
import case_study_hospital_management.entity.AppointmentEntity;
import case_study_hospital_management.entity.DoctorEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AppointmentRepository extends IRepository<AppointmentEntity> {
    List<AppointmentEntity> findByDoctorAndDate(String doctorId, LocalDate appointmentDate);
    Map<String, Boolean> findEmptyAppointmentsByDoctorAndDate(List<AppointmentEntity> appointments, DoctorEntity doctor);
    List<AppointmentEntity> findAllAppointmentByDoctorId(String id);
    List<AppointmentEntity> findAllAppointmentByPatientId(String id);
    List<AppointmentEntity> findByKeywordOfPatient(String keyword);
    List<AppointmentEntity> findByKeywordOfDoctor(String keyword);
    List<AppointmentEntity> findByStatus(AppointmentStatus status);
    List<AppointmentEntity> findByDate(LocalDate appointmentDate);
    boolean reschedule(AppointmentEntity appointment, AppointmentEntity rescheduleAppointment);
}

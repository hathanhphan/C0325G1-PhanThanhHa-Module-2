package case_study_hospital_management.repository;

import case_study_hospital_management.common.enums.DoctorSpecialization;
import case_study_hospital_management.entity.AppointmentEntity;
import case_study_hospital_management.entity.DoctorEntity;
import case_study_hospital_management.entity.PatientEntity;

import java.util.List;
import java.util.Map;

public interface DoctorRepository extends IRepository<DoctorEntity> {
    List<DoctorEntity> findByName(String name);
    List<AppointmentEntity> findALlWorkSchedule(String id);
    List<DoctorEntity> findBySpecialization(DoctorSpecialization doctorSpecialization);
    List<DoctorEntity> findByYearOfExperience(int numberOfYear);
    List<DoctorEntity> findByPhoneNumber(String phoneNumber);
}

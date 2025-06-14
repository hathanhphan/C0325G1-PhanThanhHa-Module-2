package case_study_hospital_management.repository;

import case_study_hospital_management.entity.AppointmentEntity;
import case_study_hospital_management.entity.PatientEntity;

import java.util.List;
import java.util.Map;

public interface PatientRepository extends IRepository<PatientEntity> {
    List<PatientEntity> findByName(String name);
    List<PatientEntity> findByPhoneNumber(String phoneNumber);
    List<PatientEntity> findByKeyword(String keyword);
    Map<String, Integer> statisticByAge();
    Map<String, Integer> statisticByGender();
    Map<String, Integer> statisticByBloodType();
    List<AppointmentEntity> findAllAppointmentByPatientId(String id);
}

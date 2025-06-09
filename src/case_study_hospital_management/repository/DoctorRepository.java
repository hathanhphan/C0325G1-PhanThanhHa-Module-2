package case_study_hospital_management.repository;

import case_study_hospital_management.common.enums.DoctorSpecialization;
import case_study_hospital_management.entity.AppointmentEntity;
import case_study_hospital_management.entity.DoctorEntity;
import case_study_hospital_management.entity.PatientEntity;

import java.util.List;
import java.util.Map;

public interface DoctorRepository extends IRepository<DoctorEntity> {
    List<DoctorEntity> findByName(String name);
    List<DoctorEntity> findBySpecialization(DoctorSpecialization doctorSpecialization);
    List<DoctorEntity> findByYearOfExperience(int numberOfYear);
    List<DoctorEntity> findByPhoneNumber(String phoneNumber);
    List<DoctorEntity> findByKeyword(String keyword);
    List<AppointmentEntity> findAllAppointmentByDoctorId(String id);
    Map<String, Integer> statisticBySpec();
    Map<String, Integer> statisticByYearExp();
    Map<String, Integer> statisticByNumOfAppointment();
}

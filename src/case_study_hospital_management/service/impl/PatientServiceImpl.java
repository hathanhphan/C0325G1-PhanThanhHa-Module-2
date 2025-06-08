package case_study_hospital_management.service.impl;

import case_study_hospital_management.common.enums.DayOfWeekInVietnamese;
import case_study_hospital_management.entity.AppointmentEntity;
import case_study_hospital_management.entity.PatientEntity;
import case_study_hospital_management.repository.PatientRepository;
import case_study_hospital_management.repository.impl.PatientRepositoryImpl;
import case_study_hospital_management.service.PatientService;
import case_study_hospital_management.util.ConsoleUtil;
import case_study_hospital_management.util.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PatientServiceImpl implements PatientService {
    private static PatientServiceImpl instance;
    private final PatientRepository patientRepository;

    private PatientServiceImpl() {
        patientRepository = PatientRepositoryImpl.getInstance();
    }

    public static synchronized PatientServiceImpl getInstance() {
        if (instance == null) {
            instance = new PatientServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean registerPatient(PatientEntity patient) {
        patient.setId(generateId());
        patient.setRegistrationDate(LocalDate.now());
        return patientRepository.save(patient);
    }

    @Override
    public List<PatientEntity> findByName(String name) {
        return patientRepository.findByName(name);
    }

    @Override
    public List<PatientEntity> findByPhoneNumber(String phoneNumber) {
        return patientRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<PatientEntity> findByKeyword(String keyword) {
        return patientRepository.findByKeyword(keyword);
    }

    @Override
    public List<AppointmentEntity> findAllAppointmentByPatientId(String id) {
        return patientRepository.findAllAppointmentByPatientId(id);
    }

    @Override
    public Map<String, List<AppointmentEntity>> groupAppointmentByDate(List<AppointmentEntity> appointments) {
        Map<String, List<AppointmentEntity>> groupAppointmentsByDate = new LinkedHashMap<>();
        String dayOfWeek = "";
        String currentDayOfWeek;
        String key = "";
        List<AppointmentEntity> groups;
        for (AppointmentEntity appointment : appointments) {
            currentDayOfWeek = DayOfWeekInVietnamese.fromCode(appointment.getAppointmentDate().getDayOfWeek().toString()).getDisplayName();
            if (dayOfWeek.isEmpty() || !dayOfWeek.equalsIgnoreCase(currentDayOfWeek)) {
                dayOfWeek = currentDayOfWeek;
                if (appointment.getAppointmentDate().equals(LocalDate.now())) {
                    key = String.format("%s, %s (hôm nay): ", dayOfWeek, DateUtil.formatDate(appointment.getAppointmentDate()));
                } else {
                    key = String.format("%s, %s: ", dayOfWeek, DateUtil.formatDate(appointment.getAppointmentDate()));
                }
            }
            if (groupAppointmentsByDate.containsKey(key)) {
                groupAppointmentsByDate.get(key).add(appointment);
            } else {
                groups = new ArrayList<>();
                groups.add(appointment);
                groupAppointmentsByDate.put(key, groups);
            }
        }
        return groupAppointmentsByDate;
    }

    @Override
    public List<PatientEntity> getAll() {
        return patientRepository.findAll().stream().filter(p -> !p.getDeleted()).toList();
    }

    @Override
    public PatientEntity findById(String id) {
        return patientRepository.findById(id);
    }

    @Override
    public boolean update(PatientEntity patient) {
        return patientRepository.update(patient);
    }

    @Override
    public boolean delete(String id) {
        return patientRepository.delete(id);
    }

    @Override
    public String generateId() {
        List<PatientEntity> patients = patientRepository.findAll();
        int maxId = patients.stream().mapToInt(p -> Integer.parseInt(p.getId().substring(1))).max().orElse(0);
        return String.format("P%06d", maxId + 1);
    }

    @Override
    public Map<String, Integer> statisticByAge() {
        return patientRepository.statisticByAge();
    }

    @Override
    public Map<String, Integer> statisticByGender() {
        return patientRepository.statisticByGender();
    }

    @Override
    public Map<String, Integer> statisticByBloodType() {
        return patientRepository.statisticByBloodType();
    }
}

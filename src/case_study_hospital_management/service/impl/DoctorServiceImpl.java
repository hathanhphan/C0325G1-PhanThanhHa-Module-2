package case_study_hospital_management.service.impl;

import case_study_hospital_management.common.enums.DayOfWeekInVietnamese;
import case_study_hospital_management.common.enums.DoctorSpecialization;
import case_study_hospital_management.entity.AppointmentEntity;
import case_study_hospital_management.entity.DoctorEntity;
import case_study_hospital_management.repository.DoctorRepository;
import case_study_hospital_management.repository.impl.DoctorRepositoryImpl;
import case_study_hospital_management.service.DoctorService;
import case_study_hospital_management.util.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DoctorServiceImpl implements DoctorService {
    private static DoctorServiceImpl instance;
    private final DoctorRepository doctorRepository;

    private DoctorServiceImpl() {
        doctorRepository = DoctorRepositoryImpl.getInstance();
    }

    public static synchronized DoctorServiceImpl getInstance() {
        if (instance == null) {
            instance = new DoctorServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean add(DoctorEntity doctor) {
        doctor.setId(generateId());
        return doctorRepository.save(doctor);
    }

    @Override
    public List<DoctorEntity> findByName(String name) {
        return doctorRepository.findByName(name);
    }

    @Override
    public List<AppointmentEntity> findAllAppointmentByDoctorId(String id) {
        return doctorRepository.findAllAppointmentByDoctorId(id);
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
    public List<DoctorEntity> findBySpecialization(DoctorSpecialization doctorSpecialization) {
        return doctorRepository.findBySpecialization(doctorSpecialization);
    }

    @Override
    public List<DoctorEntity> findByYearOfExperience(int numberOfYear) {
        return doctorRepository.findByYearOfExperience(numberOfYear);
    }

    @Override
    public List<DoctorEntity> findByPhoneNumber(String phoneNumber) {
        return doctorRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<DoctorEntity> findByKeyword(String keyword) {
        return doctorRepository.findByKeyword(keyword);
    }

    @Override
    public List<DoctorEntity> getAll() {
        return doctorRepository.findAll().stream().filter(d -> !d.getDeleted()).toList();
    }

    @Override
    public DoctorEntity findById(String id) {
        return doctorRepository.findById(id);
    }

    @Override
    public boolean update(DoctorEntity doctorEntity) {
        return doctorRepository.update(doctorEntity);
    }

    @Override
    public boolean delete(String id) {
        return doctorRepository.delete(id);
    }

    @Override
    public String generateId() {
        List<DoctorEntity> doctors = doctorRepository.findAll();
        int maxId = doctors.stream().mapToInt(d -> Integer.parseInt(d.getId().substring(1))).max().orElse(0);
        return String.format("D%06d", maxId + 1);
    }

    @Override
    public Map<String, Integer> statisticBySpec() {
        return doctorRepository.statisticBySpec();
    }

    @Override
    public Map<String, Integer> statisticByYearExp() {
        return doctorRepository.statisticByYearExp();
    }

    @Override
    public Map<String, Integer> statisticByNumOfAppointment() {
        return doctorRepository.statisticByNumOfAppointment();
    }
}

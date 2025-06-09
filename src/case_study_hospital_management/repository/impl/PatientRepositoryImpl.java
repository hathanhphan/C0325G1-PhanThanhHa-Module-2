package case_study_hospital_management.repository.impl;

import case_study_hospital_management.common.constants.ConfigurationConstants;
import case_study_hospital_management.common.enums.BloodType;
import case_study_hospital_management.entity.AppointmentEntity;
import case_study_hospital_management.entity.PatientEntity;
import case_study_hospital_management.repository.AppointmentRepository;
import case_study_hospital_management.repository.PatientRepository;
import case_study_hospital_management.util.ConsoleUtil;
import case_study_hospital_management.util.PersonHelper;
import case_study_hospital_management.util.CSVUtil;
import case_study_hospital_management.util.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PatientRepositoryImpl implements PatientRepository {
    private static final AppointmentRepository appointmentRepository = AppointmentRepositoryImpl.getInstance();
    private static PatientRepositoryImpl instance;
    private final String fileName;

    private PatientRepositoryImpl() {
        this.fileName = ConfigurationConstants.PATIENTS_FILE;
    }

    public static synchronized PatientRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new PatientRepositoryImpl();
        }
        return instance;
    }

    private List<PatientEntity> getCurrentList() {
        return findAll().stream().filter(p -> !p.getDeleted()).toList();
    }

    @Override
    public List<PatientEntity> findByName(String name) {
        return getCurrentList().stream().filter(p -> p.getFullName().toLowerCase().contains(name.toLowerCase())).toList();
    }

    @Override
    public List<PatientEntity> findByPhoneNumber(String phoneNumber) {
        return getCurrentList().stream().filter(p -> p.getPhoneNumber().contains(phoneNumber)).toList();
    }

    @Override
    public List<PatientEntity> findByKeyword(String keyword) {
        List<PatientEntity> foundPatients = new ArrayList<>();
        foundPatients.addAll(findByName(keyword));
        foundPatients.addAll(findByPhoneNumber(keyword));
        PatientEntity foundPatientById = findById(keyword);
        if (foundPatientById != null) {
            foundPatients.add(foundPatientById);
        }
        return foundPatients.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<PatientEntity> findAll() {
        List<PatientEntity> patients = new ArrayList<>();
        List<String> lines = CSVUtil.readCSVFile(fileName);
        for (String line : lines) {
            String[] properties = CSVUtil.parseCsvLine(line);
            if (properties.length < 11) {
                continue;
            }
            try {
                PatientEntity patient = new PatientEntity(
                        properties[0],
                        properties[1],
                        properties[2].equals("null") ? null : Boolean.parseBoolean(properties[2]),
                        properties[3],
                        DateUtil.parseDate(properties[4]),
                        properties[5],
                        properties[6],
                        BloodType.fromDisplayName(properties[7]),
                        properties[8],
                        DateUtil.parseDate(properties[9])
                );
                patient.setDeleted(Boolean.parseBoolean(properties[10]));
                patients.add(patient);
            } catch (RuntimeException e) {
                ConsoleUtil.printlnYellow("Đọc không thành công dữ liệu: " + line);
            }
        }
        return patients;
    }

    @Override
    public int findIndexById(String id) {
        List<PatientEntity> patients = getCurrentList();
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public PatientEntity findById(String id) {
        List<PatientEntity> patients = getCurrentList();
        for (PatientEntity patient : patients) {
            if (patient.getId().equals(id)) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public boolean save(PatientEntity patient) {
        List<PatientEntity> patients = findAll();
        patients.add(patient);
        return CSVUtil.writeCSVFile(fileName, patients);
    }

    @Override
    public boolean update(PatientEntity patient) {
        List<PatientEntity> patients = findAll();
        int foundIndex = findIndexById(patient.getId());
        if (foundIndex > -1) {
            patients.set(foundIndex, patient);
            return CSVUtil.writeCSVFile(fileName, patients);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        List<PatientEntity> patients = new ArrayList<>(findAll());
        int foundIndex = findIndexById(id);
        if (foundIndex > -1) {
            PatientEntity patient = patients.get(foundIndex);
            patient.setDeleted(true);
            patients.set(foundIndex, patient);
            return CSVUtil.writeCSVFile(fileName, patients);
        }
        return false;
    }

    @Override
    public Map<String, Integer> statisticByAge() {
        List<PatientEntity> patients = getCurrentList();
        int currentYear = LocalDate.now().getYear();
        return patients.stream()
                .map(p -> {
                    int age = currentYear - p.getDob().getYear();
                    return PersonHelper.getAgeGroup(age);
                })
                .collect(Collectors.toMap(
                        group -> group,
                        group -> 1,
                        Integer::sum
                ));
    }

    @Override
    public Map<String, Integer> statisticByGender() {
        List<PatientEntity> patients = getCurrentList();
        return patients.stream()
                .map(p -> PersonHelper.getGenderDisplay(p.getGender()))
                .collect(Collectors.toMap(
                        group -> group,
                        group -> 1,
                        Integer::sum
                ));
    }

    @Override
    public Map<String, Integer> statisticByBloodType() {
        List<PatientEntity> patients = getCurrentList();
        return patients.stream()
                .map(p -> p.getBloodType().getDisplayName())
                .collect(Collectors.toMap(
                        group -> group,
                        group -> 1,
                        Integer::sum
                ));

    }

    @Override
    public List<AppointmentEntity> findAllAppointmentByPatientId(String id) {
        return appointmentRepository.findAllAppointmentByPatientId(id);
    }
}

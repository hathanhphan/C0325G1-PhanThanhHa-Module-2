package case_study_hospital_management.repository.impl;

import case_study_hospital_management.common.constants.ConfigurationConstants;
import case_study_hospital_management.common.enums.DoctorSpecialization;
import case_study_hospital_management.entity.AppointmentEntity;
import case_study_hospital_management.entity.DoctorEntity;
import case_study_hospital_management.entity.PatientEntity;
import case_study_hospital_management.repository.AppointmentRepository;
import case_study_hospital_management.repository.DoctorRepository;
import case_study_hospital_management.util.CSVUtil;
import case_study_hospital_management.util.ConsoleUtil;

import java.util.*;
import java.util.stream.Collectors;

public class DoctorRepositoryImpl implements DoctorRepository {
    private static final AppointmentRepository appointmentRepository = AppointmentRepositoryImpl.getInstance();
    private static DoctorRepositoryImpl instance;
    private final String fileName;

    private DoctorRepositoryImpl() {
        this.fileName = ConfigurationConstants.DOCTORS_FILE;
    }

    public static synchronized DoctorRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new DoctorRepositoryImpl();
        }
        return instance;
    }

    private List<DoctorEntity> getCurrentList() {
        return findAll().stream().filter(p -> !p.getDeleted()).toList();
    }

    @Override
    public List<DoctorEntity> findByName(String name) {
        return getCurrentList().stream().filter(d -> d.getFullName().toLowerCase().contains(name.toLowerCase())).toList();
    }

    @Override
    public List<AppointmentEntity> findAllAppointmentByDoctorId(String id) {
        return appointmentRepository.findAllAppointmentByDoctorId(id);
    }

    @Override
    public List<DoctorEntity> findBySpecialization(DoctorSpecialization doctorSpecialization) {
        return getCurrentList().stream().filter(d -> d.getSpecialization().equals(doctorSpecialization)).toList();
    }

    @Override
    public List<DoctorEntity> findByYearOfExperience(int numberOfYear) {
        return getCurrentList().stream().filter(d -> d.getYearOfExperience() == numberOfYear).toList();
    }

    @Override
    public List<DoctorEntity> findByPhoneNumber(String phoneNumber) {
        return getCurrentList().stream().filter(d -> d.getPhoneNumber().equals(phoneNumber)).toList();
    }

    @Override
    public List<DoctorEntity> findByKeyword(String keyword) {
        String finalKeyword = keyword.toLowerCase();
        List<DoctorEntity> foundDoctors = new ArrayList<>();
        foundDoctors.addAll(findByName(finalKeyword));
        foundDoctors.addAll(findByPhoneNumber(finalKeyword));
        foundDoctors.addAll(getCurrentList().stream().filter(d -> d.getSpecialization().getDisplayName().toLowerCase().contains(finalKeyword)).toList());
        DoctorEntity foundDoctorById = findById(keyword);
        if (foundDoctorById != null) {
            foundDoctors.add(foundDoctorById);
        }
        return foundDoctors.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<DoctorEntity> findAll() {
        List<DoctorEntity> doctors = new ArrayList<>();
        List<String> lines = CSVUtil.readCSVFile(fileName);
        for (String line : lines) {
            String[] properties = CSVUtil.parseCsvLine(line);
            if (properties.length < 11) {
                continue;
            }
            try {
                DoctorEntity doctor = new DoctorEntity(
                        properties[0],
                        properties[1],
                        properties[2].equals("null") ? null : Boolean.parseBoolean(properties[2]),
                        properties[3],
                        DoctorSpecialization.fromDisplayName(properties[4]),
                        properties[5],
                        properties[6],
                        Integer.parseInt(properties[7]),
                        properties[8],
                        Double.parseDouble(properties[9])
                );
                doctor.setDeleted(Boolean.parseBoolean(properties[10]));
                doctors.add(doctor);
            } catch (RuntimeException e) {
                ConsoleUtil.printlnYellow("Đọc không thành công dữ liệu: " + line);
            }
        }
        return doctors;
    }

    @Override
    public int findIndexById(String id) {
        List<DoctorEntity> doctors = getCurrentList();
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public DoctorEntity findById(String id) {
        List<DoctorEntity> doctors = getCurrentList();
        for (DoctorEntity doctor : doctors) {
            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }
        return null;
    }

    @Override
    public boolean save(DoctorEntity doctor) {
        List<DoctorEntity> doctors = findAll();
        doctors.add(doctor);
        return CSVUtil.writeCSVFile(fileName, doctors);
    }

    @Override
    public boolean update(DoctorEntity doctor) {
        List<DoctorEntity> doctors = findAll();
        int foundIndex = findIndexById(doctor.getId());
        if (foundIndex > -1) {
            doctors.set(foundIndex, doctor);
            return CSVUtil.writeCSVFile(fileName, doctors);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        List<DoctorEntity> doctors = new ArrayList<>(findAll());
        int foundIndex = findIndexById(id);
        if (foundIndex > -1) {
            DoctorEntity doctor = doctors.get(foundIndex);
            doctor.setDeleted(true);
            doctors.set(foundIndex, doctor);
            return CSVUtil.writeCSVFile(fileName, doctors);
        }
        return false;
    }

    @Override
    public Map<String, Integer> statisticBySpec() {
        List<DoctorEntity> doctors = getCurrentList();
        return doctors.stream()
                .sorted(Comparator.comparing(a -> a.getSpecialization().getDisplayName()))
                .map(p -> p.getSpecialization().getDisplayName())
                .collect(Collectors.toMap(
                        group -> group,
                        group -> 1,
                        Integer::sum,
                        LinkedHashMap::new
                ));
    }

    @Override
    public Map<String, Integer> statisticByYearExp() {
        List<DoctorEntity> doctors = getCurrentList();
        return doctors.stream()
                .sorted(Comparator.comparing(DoctorEntity::getYearOfExperience))
                .map(p -> p.getYearOfExperience().toString() + " năm")
                .collect(Collectors.toMap(
                        group -> group,
                        group -> 1,
                        Integer::sum,
                        LinkedHashMap::new
                ));
    }

    @Override
    public Map<String, Integer> statisticByNumOfAppointment() {
        List<AppointmentEntity> appointments = appointmentRepository.findAll().stream().filter(a -> !a.getDeleted()).toList();
        return appointments.stream()
                .sorted(Comparator.comparing(e -> e.getDoctor().getFullName()))
                .map(AppointmentEntity::getDoctorId)
                .collect(Collectors.toMap(
                        group -> findById(group).getFullName() + " (" + group + ")",
                        group -> 1,
                        Integer::sum,
                        LinkedHashMap::new
                ));
    }
}

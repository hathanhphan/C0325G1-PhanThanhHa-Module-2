package case_study_hospital_management.repository.impl;

import case_study_hospital_management.common.constants.ConfigurationConstants;
import case_study_hospital_management.common.constants.WorkingHoursConstants;
import case_study_hospital_management.common.enums.AppointmentStatus;
import case_study_hospital_management.common.enums.DoctorSpecialization;
import case_study_hospital_management.entity.AppointmentEntity;
import case_study_hospital_management.entity.DoctorEntity;
import case_study_hospital_management.repository.AppointmentRepository;
import case_study_hospital_management.repository.DoctorRepository;
import case_study_hospital_management.repository.PatientRepository;
import case_study_hospital_management.util.CSVUtil;
import case_study_hospital_management.util.DateUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class AppointmentRepositoryImpl implements AppointmentRepository {

    private static AppointmentRepositoryImpl instance;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final String fileName;

    private AppointmentRepositoryImpl() {
        this.fileName = ConfigurationConstants.APPOINTMENTS_FILE;
        patientRepository = PatientRepositoryImpl.getInstance();
        doctorRepository = DoctorRepositoryImpl.getInstance();
    }

    public static synchronized AppointmentRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new AppointmentRepositoryImpl();
        }
        return instance;
    }

    private List<AppointmentEntity> getCurrentList() {
        return findAll().stream().filter(a -> !a.getDeleted()).toList();
    }

    @Override
    public List<AppointmentEntity> findAll() {
        List<AppointmentEntity> appointments = new ArrayList<>();
        List<String> lines = CSVUtil.readCSVFile(fileName);
        for (String line : lines) {
            if (lines.isEmpty()) {
                continue;
            }
            String[] properties = CSVUtil.parseCsvLine(line);
            AppointmentEntity appointment = new AppointmentEntity(
                    properties[0],
                    properties[1],
                    properties[2],
                    DateUtil.parseDate(properties[3]),
                    properties[4],
                    AppointmentStatus.fromCode(properties[5]),
                    properties[6],
                    properties[7],
                    properties[8],
                    properties[9],
                    properties[10]
            );
            appointment.setDeleted(Boolean.parseBoolean(properties[11]));
            appointment.setPatient(patientRepository.findById(appointment.getPatientId()));
            appointment.setDoctor(doctorRepository.findById(appointment.getDoctorId()));
            appointments.add(appointment);
        }
        return appointments;
    }

    @Override
    public int findIndexById(String id) {
        List<AppointmentEntity> appointments = getCurrentList();
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public AppointmentEntity findById(String id) {
        List<AppointmentEntity> appointments = getCurrentList();
        for (AppointmentEntity appointment : appointments) {
            if (appointment.getId().equals(id)) {
                return appointment;
            }
        }
        return null;
    }

    @Override
    public boolean save(AppointmentEntity appointment) {
        List<AppointmentEntity> appointments = findAll();
        appointments.add(appointment);
        return CSVUtil.writeCSVFile(fileName, appointments);
    }

    @Override
    public boolean update(AppointmentEntity appointment) {
        List<AppointmentEntity> appointments = findAll();
        int foundIndex = findIndexById(appointment.getId());
        if (foundIndex > -1) {
            appointments.set(foundIndex, appointment);
            return CSVUtil.writeCSVFile(fileName, appointments);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        List<AppointmentEntity> appointments = findAll();
        int foundIndex = findIndexById(id);
        if (foundIndex > -1) {
            AppointmentEntity appointment = appointments.get(foundIndex);
            appointment.setDeleted(true);
            appointments.set(foundIndex, appointment);
            return CSVUtil.writeCSVFile(fileName, appointments);
        }
        return false;
    }

    @Override
    public List<AppointmentEntity> findByDoctorAndDate(String doctorId, LocalDate appointmentDate) {
        List<AppointmentEntity> appointments = getCurrentList();
        return appointments.stream().filter(a -> a.getDoctorId().equals(doctorId) && a.getAppointmentDate().equals(appointmentDate)).toList();
    }

    @Override
    public Map<String, Boolean> findEmptyAppointmentsByDoctorAndDate(List<AppointmentEntity> appointments, DoctorEntity doctor) {
        Map<String, Boolean> appointmentsPerDay = new TreeMap<>();
        String[] workingTimes = doctor.getWorkingHours().split("-");
        LocalTime startTime = DateUtil.parseTime(workingTimes[0]);
        LocalTime endTime = DateUtil.parseTime(workingTimes[1]);
        String appointmentTime;
        while (startTime != null && endTime != null && startTime.isBefore(endTime)) {
            appointmentTime = DateUtil.formatTime(startTime) + "-" + DateUtil.formatTime(startTime.plusMinutes(WorkingHoursConstants.STANDARD_TIME_PER_APPOINTMENT));
            appointmentsPerDay.put(appointmentTime, true);
            startTime = startTime.plusMinutes(WorkingHoursConstants.STANDARD_TIME_PER_APPOINTMENT);
        }
        appointments.forEach(a -> {
            if (appointmentsPerDay.containsKey(a.getAppointmentTime()) && !a.getStatus().equals(AppointmentStatus.CANCELLED)) {
                appointmentsPerDay.put(a.getAppointmentTime(), false);
            }
        });
        return appointmentsPerDay;
    }

    @Override
    public List<AppointmentEntity> findAllAppointmentByDoctorId(String id) {
        List<AppointmentEntity> appointments = getCurrentList();
        return appointments.stream().filter(a -> a.getDoctorId().equals(id)).sorted(Comparator.comparing(AppointmentEntity::getAppointmentDate).reversed()).toList();
    }

    @Override
    public List<AppointmentEntity> findAllAppointmentByPatientId(String id) {
        List<AppointmentEntity> appointments = getCurrentList();
        return appointments.stream().filter(a -> a.getPatientId().equals(id)).sorted(Comparator.comparing(AppointmentEntity::getAppointmentDate).reversed()).toList();
    }

    @Override
    public List<AppointmentEntity> findByKeywordOfPatient(String keyword) {
        List<AppointmentEntity> appointments = getCurrentList();
        String finalKeyword = keyword.toLowerCase();
        return appointments.stream().filter(
                a -> a.getPatient().getId().toLowerCase().equals(keyword)
                    || a.getPatient().getFullName().toLowerCase().contains(finalKeyword)
                    || a.getPatient().getPhoneNumber().toLowerCase().contains(finalKeyword)
        ).toList();
    }

    @Override
    public List<AppointmentEntity> findByKeywordOfDoctor(String keyword) {
        List<AppointmentEntity> appointments = getCurrentList();
        String finalKeyword = keyword.toLowerCase();
        return appointments.stream().filter(
                a -> a.getDoctor().getId().toLowerCase().equals(keyword)
                        || a.getDoctor().getFullName().toLowerCase().contains(finalKeyword)
                        || a.getDoctor().getPhoneNumber().toLowerCase().contains(finalKeyword)
        ).toList();
    }

    @Override
    public List<AppointmentEntity> findByStatus(AppointmentStatus status) {
        List<AppointmentEntity> appointments = getCurrentList();
        return appointments.stream().filter(a -> a.getStatus().equals(status)).toList();
    }

    @Override
    public List<AppointmentEntity> findByDate(LocalDate appointmentDate) {
        List<AppointmentEntity> appointments = getCurrentList();
        return appointments.stream().filter(a -> a.getAppointmentDate().equals(appointmentDate)).toList();
    }
}

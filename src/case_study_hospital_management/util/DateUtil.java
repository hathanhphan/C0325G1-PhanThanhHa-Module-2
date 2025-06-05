package case_study_hospital_management.util;

import case_study_hospital_management.common.constants.Constants;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeParseException;


public class DateUtil {
    public static String formatDate(LocalDate date) {
        return date != null ? date.format(Constants.DATE_FORMATTER) : "";
    }

    public static String formatTime(LocalTime time) {
        return time != null ? time.format(Constants.TIME_FORMATTER) : "";
    }

    public static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, Constants.DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format: " + dateString);
            return null;
        }
    }

    public static LocalTime parseTime(String timeString) {
        try {
            return LocalTime.parse(timeString, Constants.TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            System.err.println("Invalid time format: " + timeString);
            return null;
        }
    }

    public static boolean isValidAge(LocalDate birthDate) {
        return birthDate != null && birthDate.isBefore(LocalDate.now())
                && Period.between(birthDate, LocalDate.now()).getYears() < 150;
    }
}

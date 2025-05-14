package util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

/**
 * Utility class providing methods for validating various types of input.
 * This class cannot be instantiated and contains only static methods.
 */
public class Validation {

    // Private constructor to prevent instantiation
    private Validation() {
    }

    /**
     * Checks if the input string is not null, not empty, and contains at least one non-whitespace character.
     *
     * @param str The string to check
     * @return true if the string is not null, not empty, and has at least one non-whitespace character, false otherwise
     */
    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }

    /**
     * Checks if the input string can be parsed as a number (integer or decimal).
     *
     * @param str The string to check
     * @return true if the string is a valid number, false otherwise
     */
    public static boolean isNumber(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }

        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the input string can be parsed as an integer.
     *
     * @param str The string to check
     * @return true if the string is a valid integer, false otherwise
     */
    public static boolean isInteger(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }

        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the input string has at least the specified minimum length.
     *
     * @param str       The string to check
     * @param minLength The minimum length required
     * @return true if the string length is at least minLength, false otherwise
     */
    public static boolean hasMinLength(String str, int minLength) {
        return str != null && str.length() >= minLength;
    }

    /**
     * Checks if the input string does not exceed the specified maximum length.
     *
     * @param str       The string to check
     * @param maxLength The maximum length allowed
     * @return true if the string length is at most maxLength, false otherwise
     */
    public static boolean hasMaxLength(String str, int maxLength) {
        return str != null && str.length() <= maxLength;
    }

    /**
     * Checks if the numeric value of the input string is greater than (or equal to) the minimum value.
     *
     * @param str       The string to check
     * @param min       The minimum value
     * @param equalSign If true, also returns true when the value equals min
     * @return true if the value is greater than (or equal to, if equalSign is true) min, false otherwise
     */
    public static boolean isGreaterThanMin(String str, int min, boolean equalSign) {
        if (!isNumber(str)) {
            return false;
        }

        double value = Double.parseDouble(str);
        return equalSign ? value >= min : value > min;
    }

    public static boolean isGreaterThanMin(String str, int min) {
        return isGreaterThanMin(str, min, false);
    }

    /**
     * Checks if the numeric value of the input string is less than (or equal to) the maximum value.
     *
     * @param str       The string to check
     * @param max       The maximum value
     * @param equalSign If true, also returns true when the value equals max
     * @return true if the value is less than (or equal to, if equalSign is true) max, false otherwise
     */
    public static boolean isLessThanMax(String str, int max, boolean equalSign) {
        if (!isNumber(str)) {
            return false;
        }
        double value = Double.parseDouble(str);
        return equalSign ? value <= max : value < max;
    }

    public static boolean isLessThanMax(String str, int max) {
        return isLessThanMax(str, max, false);
    }

    /**
     * Checks if the input string is a valid phone number.
     * This method uses a basic pattern for phone numbers.
     *
     * @param str The string to check
     * @return true if the string is a valid phone number, false otherwise
     */
    public static boolean isPhoneNumber(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }

        // Basic pattern: allows formats like (123) 456-7890, 123-456-7890, 1234567890
        Pattern pattern = Pattern.compile("^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$");
        return pattern.matcher(str).matches();
    }

    /**
     * Checks if the input string matches a specific phone number pattern.
     *
     * @param str     The string to check
     * @param pattern The pattern to match against
     * @return true if the string matches the pattern, false otherwise
     */
    public static boolean isPhoneNumberMatchPattern(String str, Pattern pattern) {
        if (str == null || pattern == null) {
            return false;
        }

        return pattern.matcher(str).matches();
    }

    /**
     * Checks if the input string is a valid email address.
     *
     * @param str The string to check
     * @return true if the string is a valid email address, false otherwise
     */
    public static boolean isEmail(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }

        // Email pattern based on RFC 5322 (simplified)
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        return pattern.matcher(str).matches();
    }

    /**
     * Checks if the input string matches a specific pattern.
     *
     * @param str     The string to check
     * @param pattern The pattern to match against
     * @return true if the string matches the pattern, false otherwise
     */
    public static boolean matchPattern(String str, Pattern pattern) {
        if (str == null || pattern == null) {
            return false;
        }

        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * Checks if a string is unique among a list of objects of any type,
     * based on a field extracted by the provided extractor function.
     *
     * @param <T>            The type of objects in the list
     * @param str            The string to check for uniqueness
     * @param objects        The list of objects to check against
     * @param fieldExtractor Function that extracts the field value to compare with
     * @return true if the string is unique, false otherwise
     */
    public static <T> boolean isUnique(String str, ArrayList<T> objects,
                                       Function<T, String> fieldExtractor) {
        // Validate input parameters
        if (str == null || objects == null || fieldExtractor == null) {
            return false;
        }

        // Check if no object in the list has a matching field value
        return objects.stream()
                .filter(Objects::nonNull)
                .noneMatch(obj -> str.equals(fieldExtractor.apply(obj)));
    }

    /**
     * Checks if the input string represents a valid date that can be parsed as a LocalDate.
     * By default, it attempts to parse the date using the ISO_LOCAL_DATE format (yyyy-MM-dd).
     *
     * @param str The string to check
     * @return true if the string can be parsed as a valid LocalDate, false otherwise
     */
    public static boolean isValidLocalDate(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }

        try {
            LocalDate.parse(str);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}

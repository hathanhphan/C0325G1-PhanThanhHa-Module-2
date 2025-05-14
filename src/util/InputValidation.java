package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * A Laravel-inspired validation system for console input in Java.
 * This class provides a fluent interface for defining and applying validation rules.
 */
public class InputValidation {
    private static final Scanner scanner = new Scanner(System.in);

    // Map to store field values
    private final Map<String, String> values = new HashMap<>();

    // Map to store validation errors
    private final Map<String, List<String>> errors = new HashMap<>();

    // Collection of objects for uniqueness validation
    private Map<String, ArrayList<?>> collections = new HashMap<>();

    // Map of field extractors for uniqueness validation
    private Map<String, Function<Object, String>> fieldExtractors = new HashMap<>();

    /**
     * Registers a collection for uniqueness validation.
     *
     * @param <T> The type of objects in the collection
     * @param collectionName The name of the collection to register
     * @param collection The collection of objects
     * @param fieldExtractor Function to extract the field value from objects
     * @return This InputValidation instance for method chaining
     */
    public <T> InputValidation registerCollection(String collectionName, ArrayList<T> collection,
                                                  Function<T, String> fieldExtractor) {
        collections.put(collectionName, collection);
        fieldExtractors.put(collectionName, (Function<Object, String>) fieldExtractor);
        return this;
    }

    /**
     * Validates input according to specified rules.
     *
     * @param rules Map of field names to validation rules
     * @return true if validation passes, false if there are errors
     */
    public boolean validate(Map<String, String> rules) {
        errors.clear();

        for (Map.Entry<String, String> entry : rules.entrySet()) {
            String field = entry.getKey();
            String ruleString = entry.getValue();

            // Prompt for input if not already provided
            if (!values.containsKey(field)) {
                System.out.print("Enter " + formatFieldName(field) + ": ");
                values.put(field, scanner.nextLine().trim());
            }

            String value = values.get(field);

            // Process each rule
            String[] ruleList = ruleString.split("\\|");
            for (String rule : ruleList) {
                validateRule(field, value, rule);
            }
        }

        // Display errors if any
        if (!errors.isEmpty()) {
            for (Map.Entry<String, List<String>> entry : errors.entrySet()) {
                String field = entry.getKey();
                List<String> fieldErrors = entry.getValue();

                for (String error : fieldErrors) {
                    System.out.println("Error: " + error);
                }
            }
            return false;
        }

        return true;
    }

    /**
     * Validates input interactively, prompting for corrections until all rules pass.
     *
     * @param rules Map of field names to validation rules
     * @return Map of validated input values
     */
    public Map<String, String> validateInteractive(Map<String, String> rules) {
        boolean valid;
        do {
            valid = validate(rules);

            if (!valid) {
                // Prompt for corrections
                for (String field : errors.keySet()) {
                    System.out.print("Enter " + formatFieldName(field) + " again: ");
                    values.put(field, scanner.nextLine().trim());
                }
                errors.clear();
            }
        } while (!valid);

        return new HashMap<>(values);
    }

    /**
     * Gets a validated value.
     *
     * @param field The field name
     * @return The validated value
     */
    public String get(String field) {
        return values.get(field);
    }

    /**
     * Gets all validated values.
     *
     * @return Map of all validated values
     */
    public Map<String, String> all() {
        return new HashMap<>(values);
    }

    /**
     * Sets a value for a field (useful for pre-populating or testing).
     *
     * @param field The field name
     * @param value The value to set
     * @return This InputValidation instance for method chaining
     */
    public InputValidation set(String field, String value) {
        values.put(field, value);
        return this;
    }

    /**
     * Validates a single rule against a field value.
     *
     * @param field The field name
     * @param value The field value
     * @param rule The rule to validate
     */
    private void validateRule(String field, String value, String rule) {
        String[] ruleParts = rule.split(":");
        String ruleName = ruleParts[0];
        String[] ruleParams = ruleParts.length > 1 ? ruleParts[1].split(",") : new String[0];

        switch (ruleName) {
            case "required":
                if (!Validation.isNotBlank(value)) {
                    addError(field, "The " + formatFieldName(field) + " field is required.");
                }
                break;

            case "min":
                int minLength = Integer.parseInt(ruleParams[0]);
                if (!Validation.hasMinLength(value, minLength)) {
                    addError(field, "The " + formatFieldName(field) + " must be at least " + minLength + " characters.");
                }
                break;

            case "max":
                int maxLength = Integer.parseInt(ruleParams[0]);
                if (!Validation.hasMaxLength(value, maxLength)) {
                    addError(field, "The " + formatFieldName(field) + " may not be greater than " + maxLength + " characters.");
                }
                break;

            case "email":
                if (!Validation.isEmail(value)) {
                    addError(field, "The " + formatFieldName(field) + " must be a valid email address.");
                }
                break;

            case "numeric":
                if (!Validation.isNumber(value)) {
                    addError(field, "The " + formatFieldName(field) + " must be a number.");
                }
                break;

            case "integer":
                if (!Validation.isInteger(value)) {
                    addError(field, "The " + formatFieldName(field) + " must be an integer.");
                }
                break;

            case "regex":
                String patternStr = ruleParams[0];
                Pattern pattern = Pattern.compile(patternStr);
                if (!Validation.matchPattern(value, pattern)) {
                    addError(field, "The " + formatFieldName(field) + " format is invalid.");
                }
                break;

            case "unique":
                String collectionName = ruleParams[0];
                if (!collections.containsKey(collectionName)) {
                    addError(field, "Collection '" + collectionName + "' not registered for uniqueness check.");
                } else {
                    ArrayList<?> collection = collections.get(collectionName);
                    Function<Object, String> extractor = fieldExtractors.get(collectionName);

                    if (!isUnique(value, collection, extractor)) {
                        addError(field, "The " + formatFieldName(field) + " has already been taken.");
                    }
                }
                break;

            case "between":
                if (ruleParams.length >= 2) {
                    double min = Double.parseDouble(ruleParams[0]);
                    double max = Double.parseDouble(ruleParams[1]);

                    if (!Validation.isNumber(value) ||
                            Double.parseDouble(value) < min ||
                            Double.parseDouble(value) > max) {
                        addError(field, "The " + formatFieldName(field) + " must be between " + min + " and " + max + ".");
                    }
                }
                break;

            // Add more validation rules as needed
        }
    }

    /**
     * Adds an error message for a field.
     *
     * @param field The field name
     * @param message The error message
     */
    private void addError(String field, String message) {
        if (!errors.containsKey(field)) {
            errors.put(field, new ArrayList<>());
        }
        errors.get(field).add(message);
    }

    /**
     * Formats a field name for display in error messages.
     *
     * @param field The field name
     * @return Formatted field name
     */
    private String formatFieldName(String field) {
        // Convert camelCase or snake_case to space-separated words
        String formatted = field.replaceAll("([a-z])([A-Z])", "$1 $2")
                .replace("_", " ")
                .replace(".", " ");

        // Capitalize first letter of each word
        String[] words = formatted.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }

        return result.toString().trim();
    }

    /**
     * Checks if a value is unique in a collection.
     *
     * @param <T> The type of objects in the collection
     * @param value The value to check
     * @param collection The collection to check against
     * @param fieldExtractor Function to extract field values from objects
     * @return true if the value is unique, false otherwise
     */
    private <T> boolean isUnique(String value, ArrayList<?> collection, Function<Object, String> fieldExtractor) {
        for (Object obj : collection) {
            if (obj != null && value.equals(fieldExtractor.apply(obj))) {
                return false;
            }
        }
        return true;
    }
}

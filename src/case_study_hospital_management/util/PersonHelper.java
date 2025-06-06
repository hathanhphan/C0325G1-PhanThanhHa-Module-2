package case_study_hospital_management.util;

import case_study_hospital_management.common.constants.AgeGroupConstants;
import case_study_hospital_management.entity.PatientEntity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PersonHelper {
    private PersonHelper() {}
    public static String getAgeGroup(int age) {
        if (age >= AgeGroupConstants.INFANT_FROM && age <= AgeGroupConstants.INFANT_TO) {
            return AgeGroupConstants.INFANT;
        } else if (age > AgeGroupConstants.TODDLER_FROM && age <= AgeGroupConstants.TODDLER_TO) {
            return AgeGroupConstants.TODDLER;
        } else if (age >= AgeGroupConstants.CHILD_FROM && age <= AgeGroupConstants.CHILD_TO) {
            return AgeGroupConstants.CHILD;
        } else if (age >= AgeGroupConstants.TEEN_FROM && age <= AgeGroupConstants.TEEN_TO) {
            return AgeGroupConstants.TEEN;
        } else if (age >= AgeGroupConstants.YOUNG_ADULT_FROM && age <= AgeGroupConstants.YOUNG_ADULT_TO) {
            return AgeGroupConstants.YOUNG_ADULT;
        } else if (age >= AgeGroupConstants.ADULT_FROM && age <= AgeGroupConstants.ADULT_TO) {
            return AgeGroupConstants.ADULT;
        } else if (age >= AgeGroupConstants.MIDDLE_AGED_FROM && age <= AgeGroupConstants.MIDDLE_AGED_TO) {
            return AgeGroupConstants.MIDDLE_AGED;
        } else if (age >= AgeGroupConstants.SENIOR_FROM) {
            return AgeGroupConstants.SENIOR;
        }
        return "Không xác định";
    }

    public static String getGenderDisplay(Boolean gender) {
        String genderDisplay = "Khác";
        if (gender != null && gender) {
            genderDisplay = "Nam";
        } else if (gender != null && !gender) {
            genderDisplay = "Nữ";
        }
        return genderDisplay;
    }

    public static String formatVietNamCurrency(double money) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("vi", "VN"));
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);
        return formatter.format(money) + " ₫";
    }
}

package util;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

@SuppressWarnings("rawtypes")
public class InputValidation {
    private final Scanner sc = new Scanner(System.in);
    private Map<Function, String> validates = new HashMap<Function, String>();
    public void addRule(Function validateRule, String message) {
        validates.put(validateRule, message);
    }
    public String getErrorMessage() {
        return "";
    }

    public String input(String message) {
        System.out.print(message);
        return sc.nextLine();
    }

    private Boolean hasInValidRule(String inputValue, Map<Function<String, Boolean>, String> rules) {
        for (Map.Entry<Function<String, Boolean>, String> entry : rules.entrySet()) {
            if (!entry.getKey().apply(inputValue)) {
                System.out.println(entry.getValue());
                return true;
            }
        }
        return false;
    }

    public long inputLong(String message, Map<Function<String, Boolean>, String> rules) {
        Scanner sc = new Scanner(System.in);
        String inputValue;
        do {
            System.out.print(message);
            inputValue = sc.nextLine();
        } while (hasInValidRule(inputValue, rules));
        return Long.parseLong(inputValue);
    }

    public int inputInt(String message) {
        System.out.print(message);
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Vui lòng nhập một số nguyên hợp lệ!");
            return inputInt(message);
        }
    }

    public double inputDouble(String message) {
        System.out.print(message);
        try {
            return Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Vui lòng nhập một số thực hợp lệ!");
            return inputDouble(message);
        }
    }
}

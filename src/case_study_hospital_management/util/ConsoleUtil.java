package case_study_hospital_management.util;

public class ConsoleUtil {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BOLD = "\033[0;1m";
    public static final String ITALIC = "\033[3m";

    private ConsoleUtil() {}

    public static void printlnRed(String msg) {
        System.out.println(RED + msg + RESET);
    }

    public static void printlnGreen(String msg) {
        System.out.println(GREEN + msg + RESET);
    }

    public static void printlnYellow(String msg) {
        System.out.println(YELLOW + msg + RESET);
    }

    public static void printlnBold(String msg) {
        System.out.println(BOLD + msg + RESET);
    }

    public static void printlnItalic(String msg) {
        System.out.println(ITALIC + msg + RESET);
    }

    public static void printRed(String msg) {
        System.out.print(RED + msg + RESET);
    }

    public static void printGreen(String msg) {
        System.out.print(GREEN + msg + RESET);
    }

    public static void printYellow(String msg) {
        System.out.print(YELLOW + msg + RESET);
    }

    public static void printBold(String msg) {
        System.out.print(BOLD + msg + RESET);
    }

    public static void printItalic(String msg) {
        System.out.print(ITALIC + msg + RESET);
    }

    public static void println(String color, String msg) {
        System.out.println(color + msg + RESET);
    }
}

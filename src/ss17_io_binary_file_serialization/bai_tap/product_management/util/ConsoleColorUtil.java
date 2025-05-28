package ss17_io_binary_file_serialization.bai_tap.product_management.util;

public class ConsoleColorUtil {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";

    private ConsoleColorUtil() {}

    public static void printlnRed(String msg) {
        System.out.println(RED + msg + RESET);
    }

    public static void printlnGreen(String msg) {
        System.out.println(GREEN + msg + RESET);
    }

    public static void printlnYellow(String msg) {
        System.out.println(YELLOW + msg + RESET);
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

    public static void println(String color, String msg) {
        System.out.println(color + msg + RESET);
    }
}

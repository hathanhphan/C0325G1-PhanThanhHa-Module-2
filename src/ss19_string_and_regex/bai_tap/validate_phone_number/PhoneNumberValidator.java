package ss19_string_and_regex.bai_tap.validate_phone_number;

import java.util.regex.Pattern;

public class PhoneNumberValidator {
    /*
    * - Số điện thoại hợp lệ cần đạt theo mẫu sau: (xx)-(0xxxxxxxxx)
            - x là ký tự số
            - Không chứa các ký tự đặc biệt
      - Ví dụ:
            - Số điện thoại hợp lệ: (84)-(0978489648)
            - Số điện thoại không hợp lệ: (a8)-(22222222), (84)-(22b22222), (84)-(9978489648)
    * */

    private static final String PHONE_NUMBER_PATTERN = "^\\([0-9]{2}\\)-\\(0[0-9]{9}\\)$";
    private static final Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        return pattern.matcher(phoneNumber).matches();
    }

    public static void main(String[] args) {
        // Test cases hợp lệ
        String[] validPhones = {
                "(84)-(0978489648)",
                "(01)-(0123456789)",
                "(99)-(0987654321)",
                "(84)-(0909123456)"
        };

        // Test cases không hợp lệ
        String[] invalidPhones = {
                "(a8)-(0978489648)",    // Chứa chữ cái trong mã vùng
                "(84)-(22222222)",      // Thiếu số 0 đầu và thiếu 1 chữ số
                "(84)-(22b22222)",      // Chứa chữ cái trong số điện thoại
                "(84)-(9978489648)",    // Không bắt đầu bằng số 0
                "(8)-(0978489648)",     // Mã vùng chỉ có 1 chữ số
                "(844)-(0978489648)",   // Mã vùng có 3 chữ số
                "(84)-(01234567890)",   // Số điện thoại có 10 chữ số
                "(84)-(012345678)",     // Số điện thoại chỉ có 8 chữ số
                "84-0978489648",        // Thiếu dấu ngoặc
                "(84)-(0978489648",     // Thiếu dấu ngoặc đóng cuối
                " (84)-(0978489648)",   // Có khoảng trắng đầu
                "(84)-(0978489648) "    // Có khoảng trắng cuối
        };

        System.out.println("=== Số điện thoại hợp lệ ===");
        for (String phone : validPhones) {
            System.out.println(phone + ": " + isValidPhoneNumber(phone));
        }

        System.out.println("\n=== Số điện thoại không hợp lệ ===");
        for (String phone : invalidPhones) {
            System.out.println(phone + ": " + isValidPhoneNumber(phone));
        }
    }
}

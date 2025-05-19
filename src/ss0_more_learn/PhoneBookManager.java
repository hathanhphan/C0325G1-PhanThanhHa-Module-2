package ss0_more_learn;

import java.util.Scanner;
import java.util.regex.Pattern;

public class PhoneBookManager {
    // Cài đặt cấu trúc dữ liệu hash table tự triển khai
    private static class HashNode {
        String name;
        String phoneNumber;
        HashNode next;

        public HashNode(String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.next = null;
        }
    }

    private HashNode[] buckets;
    private int numBuckets;
    private int size; // số lượng số điện thoại trong danh bạ

    // Định dạng regex cho số điện thoại Việt Nam
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(0[0-9]{9})$");

    public PhoneBookManager() {
        numBuckets = 10; // Kích thước ban đầu của hash table
        buckets = new HashNode[numBuckets];
        size = 0;
    }

    /**
     * Hàm băm để chuyển đổi tên thành index trong mảng buckets
     * @param name Tên cần băm
     * @return index trong bảng băm
     */
    private int hashFunction(String name) {
        int hashCode = 0;
        for (int i = 0; i < name.length(); i++) {
            hashCode += name.charAt(i);
        }
        return hashCode % numBuckets;
    }

    /**
     * Kiểm tra xem tên có hợp lệ không
     * @param name Tên cần kiểm tra
     * @return true nếu tên hợp lệ, false nếu không
     */
    private boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Kiểm tra xem số điện thoại có hợp lệ không
     * @param phoneNumber Số điện thoại cần kiểm tra
     * @return true nếu số điện thoại hợp lệ, false nếu không
     */
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && PHONE_PATTERN.matcher(phoneNumber).matches();
    }

    /**
     * 1. Thêm số điện thoại vào bảng băm
     * @param name Tên người dùng
     * @param phoneNumber Số điện thoại
     * @return true nếu thêm thành công, false nếu tên đã tồn tại hoặc thông tin không hợp lệ
     */
    public boolean addPhoneNumber(String name, String phoneNumber) {
        // Validate tên
        if (!isValidName(name)) {
            System.out.println("Lỗi: Tên không được để trống!");
            return false;
        }

        // Validate số điện thoại
        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Lỗi: Số điện thoại không hợp lệ! Số điện thoại phải bắt đầu bằng số 0 và có 10 chữ số.");
            return false;
        }

        int bucketIndex = hashFunction(name);
        HashNode head = buckets[bucketIndex];

        // Kiểm tra xem tên đã tồn tại chưa
        while (head != null) {
            if (head.name.equals(name)) {
                System.out.println("Tên đã tồn tại trong danh bạ!");
                return false;
            }
            head = head.next;
        }

        // Thêm node mới vào đầu linked list
        size++;
        head = buckets[bucketIndex];
        HashNode newNode = new HashNode(name, phoneNumber);
        newNode.next = head;
        buckets[bucketIndex] = newNode;

        System.out.println("Đã thêm số điện thoại thành công!");
        return true;
    }

    /**
     * 2. Tìm kiếm số điện thoại theo tên trong bảng băm
     * @param name Tên người dùng cần tìm
     * @return Số điện thoại nếu tìm thấy, null nếu không tìm thấy
     */
    public String findPhoneNumber(String name) {
        if (!isValidName(name)) {
            System.out.println("Lỗi: Tên không được để trống!");
            return null;
        }

        int bucketIndex = hashFunction(name);
        HashNode head = buckets[bucketIndex];

        while (head != null) {
            if (head.name.equals(name)) {
                return head.phoneNumber;
            }
            head = head.next;
        }

        return null;
    }

    /**
     * 3. Xóa số điện thoại khỏi bảng băm theo tên
     * @param name Tên người dùng cần xóa
     * @return true nếu xóa thành công, false nếu không tìm thấy hoặc tên không hợp lệ
     */
    public boolean deletePhoneNumber(String name) {
        if (!isValidName(name)) {
            System.out.println("Lỗi: Tên không được để trống!");
            return false;
        }

        int bucketIndex = hashFunction(name);
        HashNode head = buckets[bucketIndex];
        HashNode prev = null;

        // Tìm node cần xóa
        while (head != null) {
            if (head.name.equals(name)) {
                break;
            }
            prev = head;
            head = head.next;
        }

        // Nếu không tìm thấy
        if (head == null) {
            return false;
        }

        // Xóa node
        size--;

        // Nếu node cần xóa là đầu danh sách
        if (prev == null) {
            buckets[bucketIndex] = head.next;
        } else {
            prev.next = head.next;
        }

        return true;
    }

    /**
     * 4. Hiển thị tất cả các số điện thoại trong danh bạ
     */
    public void displayAll() {
        if (size == 0) {
            System.out.println("Danh bạ trống!");
            return;
        }

        System.out.println("Danh bạ điện thoại:");
        System.out.println("------------------------------");
        System.out.printf("%-20s | %-15s\n", "Tên", "Số điện thoại");
        System.out.println("------------------------------");

        for (int i = 0; i < numBuckets; i++) {
            HashNode head = buckets[i];
            while (head != null) {
                System.out.printf("%-20s | %-15s\n", head.name, head.phoneNumber);
                head = head.next;
            }
        }
        System.out.println("------------------------------");
    }

    public static void main(String[] args) {
        PhoneBookManager phoneBook = new PhoneBookManager();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("\nQUẢN LÝ DANH BẠ ĐIỆN THOẠI");
            System.out.println("1. Thêm số điện thoại");
            System.out.println("2. Tìm kiếm số điện thoại theo tên");
            System.out.println("3. Xóa số điện thoại");
            System.out.println("4. Hiển thị tất cả số điện thoại");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng (1-5): ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Đọc bỏ dòng new line

                switch (choice) {
                    case 1:
                        System.out.print("Nhập tên: ");
                        String name = scanner.nextLine();
                        System.out.print("Nhập số điện thoại: ");
                        String phoneNumber = scanner.nextLine();
                        phoneBook.addPhoneNumber(name, phoneNumber);
                        break;
                    case 2:
                        System.out.print("Nhập tên cần tìm: ");
                        name = scanner.nextLine();
                        String result = phoneBook.findPhoneNumber(name);
                        if (result != null) {
                            System.out.println("Số điện thoại của " + name + " là: " + result);
                        } else {
                            System.out.println("Không tìm thấy " + name + " trong danh bạ!");
                        }
                        break;
                    case 3:
                        System.out.print("Nhập tên cần xóa: ");
                        name = scanner.nextLine();
                        boolean deleted = phoneBook.deletePhoneNumber(name);
                        if (deleted) {
                            System.out.println("Đã xóa " + name + " khỏi danh bạ!");
                        } else {
                            System.out.println("Không tìm thấy " + name + " trong danh bạ!");
                        }
                        break;
                    case 4:
                        phoneBook.displayAll();
                        break;
                    case 5:
                        running = false;
                        System.out.println("Đã thoát chương trình!");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn từ 1-5.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Lỗi: Vui lòng nhập số từ 1-5!");
                scanner.nextLine(); // Đọc bỏ dòng không hợp lệ để tránh lặp vô hạn
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi: " + e.getMessage());
                scanner.nextLine(); // Đọc bỏ dòng không hợp lệ
            }
        }

        scanner.close();
    }
}
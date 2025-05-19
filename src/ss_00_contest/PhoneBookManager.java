package ss_00_contest;

import java.util.Scanner;

public class PhoneBookManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneBookHashTable phoneBook = new PhoneBookHashTable(100); // Khởi tạo bảng băm với 100 bucket

        int choice;
        do {
            System.out.println("\n===== QUẢN LÝ DANH BẠ ĐIỆN THOẠI =====");
            System.out.println("1. Thêm số điện thoại");
            System.out.println("2. Tìm kiếm số điện thoại theo tên");
            System.out.println("3. Xóa số điện thoại theo tên");
            System.out.println("4. Hiển thị tất cả các số điện thoại");
            System.out.println("0. Thoát");
            System.out.print("Vui lòng chọn chức năng: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc bỏ dòng new line

            switch (choice) {
                case 1:
                    System.out.print("Nhập tên: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập số điện thoại: ");
                    String phoneNumber = scanner.nextLine();
                    phoneBook.addContact(name, phoneNumber);
                    break;

                case 2:
                    System.out.print("Nhập tên cần tìm: ");
                    String searchName = scanner.nextLine();
                    Contact foundContact = phoneBook.findContact(searchName);
                    if (foundContact != null) {
                        System.out.println("Tìm thấy: " + foundContact);
                    } else {
                        System.out.println("Không tìm thấy liên hệ với tên: " + searchName);
                    }
                    break;

                case 3:
                    System.out.print("Nhập tên cần xóa: ");
                    String removeName = scanner.nextLine();
                    boolean removed = phoneBook.removeContact(removeName);
                    if (removed) {
                        System.out.println("Đã xóa liên hệ thành công!");
                    } else {
                        System.out.println("Không tìm thấy liên hệ với tên: " + removeName);
                    }
                    break;

                case 4:
                    phoneBook.displayAllContacts();
                    break;

                case 0:
                    System.out.println("Cảm ơn đã sử dụng chương trình!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }

        } while (choice != 0);

        scanner.close();
    }
}

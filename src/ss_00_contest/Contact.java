package ss_00_contest;

// Lớp biểu diễn một liên hệ trong danh bạ
public class Contact {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Tên: " + name + ", Số điện thoại: " + phoneNumber;
    }
}

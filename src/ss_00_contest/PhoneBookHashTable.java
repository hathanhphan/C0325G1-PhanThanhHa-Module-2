package ss_00_contest;

// Lớp bảng băm (hash table) cho danh bạ điện thoại
public class PhoneBookHashTable {
    private Node[] buckets;
    private int capacity;
    private int size;

    public PhoneBookHashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new Node[capacity];
        this.size = 0;
    }

    // Hàm băm để chuyển tên thành chỉ số trong bảng
    private int hashFunction(String name) {
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 + name.charAt(i)) % capacity;
        }
        return Math.abs(hash % capacity);
    }

    // Hàm thêm số điện thoại vào bảng băm
    public void addContact(String name, String phoneNumber) {
        Contact newContact = new Contact(name, phoneNumber);
        int index = hashFunction(name);

        // Kiểm tra xem tên đã tồn tại chưa, nếu có thì cập nhật số điện thoại
        Node current = buckets[index];
        while (current != null) {
            if (current.contact.getName().equals(name)) {
                System.out.println("Tên đã tồn tại, số điện thoại đã được cập nhật.");
                current.contact = newContact;
                return;
            }
            current = current.next;
        }

        // Thêm liên hệ mới vào đầu danh sách tại bucket
        Node newNode = new Node(newContact);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
        System.out.println("Đã thêm liên hệ mới thành công!");
    }

    // Hàm tìm kiếm số điện thoại theo tên
    public Contact findContact(String name) {
        int index = hashFunction(name);
        Node current = buckets[index];

        while (current != null) {
            if (current.contact.getName().equals(name)) {
                return current.contact;
            }
            current = current.next;
        }

        return null; // Không tìm thấy
    }

    // Hàm xóa số điện thoại theo tên
    public boolean removeContact(String name) {
        int index = hashFunction(name);

        // Trường hợp bucket rỗng
        if (buckets[index] == null) {
            return false;
        }

        // Trường hợp phần tử cần xóa ở đầu bucket
        if (buckets[index].contact.getName().equals(name)) {
            buckets[index] = buckets[index].next;
            size--;
            return true;
        }

        // Trường hợp phần tử cần xóa ở giữa hoặc cuối bucket
        Node current = buckets[index];
        while (current.next != null) {
            if (current.next.contact.getName().equals(name)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }

        return false; // Không tìm thấy để xóa
    }

    // Hàm hiển thị tất cả số điện thoại trong danh bạ
    public void displayAllContacts() {
        if (size == 0) {
            System.out.println("Danh bạ trống!");
            return;
        }

        System.out.println("===== DANH BẠ ĐIỆN THOẠI =====");
        for (int i = 0; i < capacity; i++) {
            Node current = buckets[i];
            while (current != null) {
                System.out.println(current.contact);
                current = current.next;
            }
        }
        System.out.println("Tổng số liên hệ: " + size);
    }

    // Hàm lấy số lượng liên hệ trong danh bạ
    public int getSize() {
        return size;
    }
}

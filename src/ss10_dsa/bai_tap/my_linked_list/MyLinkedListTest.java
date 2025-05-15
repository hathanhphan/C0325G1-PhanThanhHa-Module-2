package ss10_dsa.bai_tap.my_linked_list;

public class MyLinkedListTest {
    public static void main(String[] args) {
        // Tạo một danh sách liên kết số nguyên
        MyLinkedList<Integer> intList = new MyLinkedList<>();

        System.out.println("=== Thêm phần tử vào danh sách ===");

        // Thêm phần tử vào đầu danh sách
        intList.addFirst(10);
        System.out.println("Sau khi thêm 10 vào đầu: size = " + intList.size());

        // Thêm phần tử vào cuối danh sách
        intList.addLast(30);
        System.out.println("Sau khi thêm 30 vào cuối: size = " + intList.size());

        // Thêm phần tử vào vị trí xác định
        intList.add(1, 20);
        System.out.println("Sau khi thêm 20 vào vị trí 1: size = " + intList.size());

        // Thêm phần tử bằng phương thức add()
        intList.add(40);
        System.out.println("Sau khi thêm 40 bằng add(): size = " + intList.size());

        // In ra tất cả phần tử
        System.out.println("\n=== Hiển thị danh sách ===");
        displayList(intList);

        // Lấy phần tử theo chỉ số
        System.out.println("\n=== Lấy phần tử theo chỉ số ===");
        System.out.println("Phần tử ở vị trí 0: " + intList.get(0));
        System.out.println("Phần tử ở vị trí 2: " + intList.get(2));
        System.out.println("Phần tử đầu tiên: " + intList.getFirst());
        System.out.println("Phần tử cuối cùng: " + intList.getLast());

        // Kiểm tra sự tồn tại của phần tử
        System.out.println("\n=== Kiểm tra phần tử trong danh sách ===");
        System.out.println("Danh sách có chứa 20 không? " + intList.contains(20));
        System.out.println("Danh sách có chứa 50 không? " + intList.contains(50));

        // Tìm vị trí của phần tử
        System.out.println("\n=== Tìm vị trí của phần tử ===");
        System.out.println("Vị trí của 30 trong danh sách: " + intList.indexOf(30));
        System.out.println("Vị trí của 50 trong danh sách: " + intList.indexOf(50)); // Trả về -1 vì không tìm thấy

        // Xóa phần tử theo chỉ số
        System.out.println("\n=== Xóa phần tử theo chỉ số ===");
        Integer removedElement = intList.remove(1);
        System.out.println("Phần tử đã xóa ở vị trí 1: " + removedElement);
        System.out.println("Danh sách sau khi xóa:");
        displayList(intList);

        // Xóa phần tử theo giá trị
        System.out.println("\n=== Xóa phần tử theo giá trị ===");
        boolean isRemoved = intList.remove(Integer.valueOf(30));
        System.out.println("Đã xóa phần tử 30: " + isRemoved);
        System.out.println("Danh sách sau khi xóa:");
        displayList(intList);

        // Thử xóa phần tử không tồn tại
        isRemoved = intList.remove(Integer.valueOf(50));
        System.out.println("Đã xóa phần tử 50: " + isRemoved);

        // Thêm nhiều phần tử khác để kiểm tra
        System.out.println("\n=== Thêm nhiều phần tử mới ===");
        intList.add(25);
        intList.add(35);
        intList.add(45);
        intList.add(55);
        System.out.println("Danh sách sau khi thêm nhiều phần tử:");
        displayList(intList);

        // Xóa tất cả phần tử
        System.out.println("\n=== Xóa tất cả phần tử ===");
        intList.clear();
        System.out.println("Kích thước sau khi xóa tất cả: " + intList.size());

        // Kiểm tra danh sách rỗng
        try {
            System.out.println("Phần tử đầu tiên: " + intList.getFirst());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // Phương thức hiển thị tất cả phần tử trong danh sách
    private static <E> void displayList(MyLinkedList<E> list) {
        System.out.print("Các phần tử trong danh sách: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println("\nKích thước danh sách: " + list.size());
    }
}

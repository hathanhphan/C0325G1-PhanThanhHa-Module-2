package ss_00_contest;

// Lớp Node để xây dựng danh sách liên kết trong trường hợp va chạm (collision)
public class Node {
    Contact contact;
    Node next;

    public Node(Contact contact) {
        this.contact = contact;
        this.next = null;
    }
}

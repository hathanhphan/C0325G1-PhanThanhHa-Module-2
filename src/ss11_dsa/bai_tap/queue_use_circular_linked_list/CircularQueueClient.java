package ss11_dsa.bai_tap.queue_use_circular_linked_list;

public class CircularQueueClient {
    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue();
        queue.enQueue(14);
        queue.enQueue(22);
        queue.enQueue(6);
        queue.enQueue(58);
        queue.display();

        System.out.println("Phần tử đã lấy ra: " + queue.deQueue());
        System.out.println("Phần tử đã lấy ra: " + queue.deQueue());

        queue.display();

        queue.enQueue(9);
        queue.enQueue(20);

        queue.display();
    }
}

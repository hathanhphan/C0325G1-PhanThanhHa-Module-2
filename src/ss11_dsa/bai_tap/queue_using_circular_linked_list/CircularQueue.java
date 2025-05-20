package ss11_dsa.bai_tap.queue_using_circular_linked_list;

public class CircularQueue {
    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;

    public CircularQueue() {
        this.front = null;
        this.rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enQueue(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
            rear.next = front;
        } else {
            newNode.next = front;
            rear.next = newNode;
            rear = newNode;
        }
    }

    public Integer deQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        int value = front.data;
        if (front == rear) {
            front = null;
            rear = null;
        } else {
            front = front.next;
            rear.next = front;
        }
        return value;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        Node temp = front;
        System.out.print("Elements in queue: ");
        do {
            System.out.print(temp.data + "\t");
            temp = temp.next;
        } while (temp != front);
        System.out.println();
    }
}

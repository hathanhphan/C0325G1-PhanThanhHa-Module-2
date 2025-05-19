package ss11_dsa.bai_tap.queue_use_circular_linked_list;

public class Queue {
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

    public Queue() {
        this.front = null;
        this.rear = null;
    }

    public void enQueue(int element) {
        Node node = new Node(element);
        if (this.front == null) {
            this.front = this.rear = node;
        } else {
            this.rear = node;
            this.rear.link = this.front;
        }
    }
    public Node deQueue() {
        if (this.front == null) {
            return null;
        } else if (this.front == this.rear) {
            this.front = this.rear = null;
        } else {
            this.front = this.front.link;
            this.rear.link = this.front;
        }
    }
    public void displayQueue() {
    }
}

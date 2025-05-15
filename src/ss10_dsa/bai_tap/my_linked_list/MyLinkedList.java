package ss10_dsa.bai_tap.my_linked_list;

public class MyLinkedList<E> {
    private Node head;
    private int numNodes;

    public MyLinkedList() {
    }

    private class Node {
        private Node next;
        private Object data;

        public Node(Object data) {
            this.data = data;
        }

        public Object getData() {
            return this.data;
        }
    }

    public void add(int index, E element) {
        if (index < 0 || index > numNodes) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + numNodes);
        }

        if (index == 0) {
            addFirst(element);
            return;
        }

        Node temp = head;
        Node holder;
        for (int i = 0; i < index - 1 && temp.next != null; i++) {
            temp = temp.next;
        }
        holder = temp.next;
        temp.next = new Node(element);
        temp.next.next = holder;
        numNodes++;
    }

    public void addFirst(E e) {
        Node temp = head;
        head = new Node(e);
        head.next = temp;
        numNodes++;
    }

    public void addLast(E e) {
        if (head == null) {
            addFirst(e);
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = new Node(e);
        numNodes++;
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index < 0 || index >= numNodes) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + numNodes);
        }

        Node temp = head;
        Object removedData;

        if (index == 0) {
            removedData = head.data;
            head = head.next;
        } else {
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            removedData = temp.next.data;
            temp.next = temp.next.next;
        }

        numNodes--;
        return (E) removedData;
    }

    public boolean remove(Object e) {
        if (head == null) {
            return false;
        }

        if (head.data.equals(e)) {
            head = head.next;
            numNodes--;
            return true;
        }

        Node temp = head;
        while (temp.next != null) {
            if (temp.next.data.equals(e)) {
                temp.next = temp.next.next;
                numNodes--;
                return true;
            }
            temp = temp.next;
        }

        return false;
    }

    public int size() {
        return numNodes;
    }

    @SuppressWarnings("unchecked")
    public E clone() {
        try {
            MyLinkedList<E> clone = (MyLinkedList<E>) super.clone();
            if (head == null) {
                return (E) clone;
            }

            clone.head = new Node(head.data);
            Node current = head.next;
            Node cloneCurrent = clone.head;

            while (current != null) {
                cloneCurrent.next = new Node(current.data);
                current = current.next;
                cloneCurrent = cloneCurrent.next;
            }

            clone.numNodes = this.numNodes;
            return (E) clone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    public boolean contains(E o) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.equals(o)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public int indexOf(E o) {
        Node temp = head;
        int index = 0;

        while (temp != null) {
            if (temp.data.equals(o)) {
                return index;
            }
            temp = temp.next;
            index++;
        }

        return -1; // Element not found
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @SuppressWarnings("unchecked")
    public E get(int i) {
        if (i < 0 || i >= numNodes) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + numNodes);
        }

        Node temp = head;
        for (int j = 0; j < i; j++) {
            temp = temp.next;
        }

        return (E) temp.data;
    }

    @SuppressWarnings("unchecked")
    public E getFirst() {
        if (head == null) {
            throw new IndexOutOfBoundsException("List is empty");
        }

        return (E) head.data;
    }

    @SuppressWarnings("unchecked")
    public E getLast() {
        if (head == null) {
            throw new IndexOutOfBoundsException("List is empty");
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        return (E) temp.data;
    }

    public void clear() {
        head = null;
        numNodes = 0;
    }
}

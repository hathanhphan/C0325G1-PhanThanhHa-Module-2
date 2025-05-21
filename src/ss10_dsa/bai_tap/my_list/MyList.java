package ss10_dsa.bai_tap.my_list;

import java.util.Arrays;

public class MyList<E> {
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;

    public MyList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public MyList(int capacity) {
        elements = new Object[capacity];
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity >= 0) {
            int newSize = this.elements.length + minCapacity;
            elements = Arrays.copyOf(elements, newSize);
        } else {
            throw new IllegalArgumentException("minCapacity " + minCapacity);
        }
    }

    public void add(int index, E element) {
        if (size == elements.length) {
            ensureCapacity(DEFAULT_CAPACITY);
        }
        if (index >= 0 && index <= size) {
            for (int i = size; i > index; i--) {
                elements[i] = elements[i - 1];
            }
            elements[index] = element;
            size++;
        } else {
            throw new IndexOutOfBoundsException("index " + index);
        }
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index >= 0 && index <= elements.length) {
            Object[] newElements = new Object[elements.length - 1];
            Object removedElement = elements[index];
            System.arraycopy(elements, 0, newElements, 0, index);
            System.arraycopy(elements, index + 1, newElements, index, elements.length - 1 - index);
            elements = newElements;
            size--;
            return (E) removedElement;
        } else {
            throw new IndexOutOfBoundsException("index " + index);
        }
    }

    public int size() {
        return size;
    }

    public boolean contains (E o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(E o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public boolean add(E e) {
        if (size == elements.length) {
            ensureCapacity(DEFAULT_CAPACITY);
        }
        elements[size++] = e;
        return true;
    }

    @SuppressWarnings("unchecked")
    public E get(int i) {
        if (i >= 0 && i < size) {
            return (E) elements[i];
        } else {
            throw new IndexOutOfBoundsException("index " + i);
        }

    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }
}

package com.omeraytekin.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
    Node<T> head, tail;
    int size;

    public LinkedList() {
        head = tail = null;
        size = 0;
    }

    public LinkedList(T[] items) {
        this();
        for (int i = 0; i < items.length; i++) {
            this.insert(items[i]);
        }
    }

    public void insert(T item) {
        if (isEmpty()) {
            head = tail = new Node<>(item);
        } else {
            Node<T> newNode = new Node<>(item, tail, null);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    /*
     * This function calls insertAt function.
     * Since target element is first element, complexity to reach first element
     * is O(1).
     * So complexity is O(1)
     */
    public void insertFirst(T item) {
        insertAt(0, item);
    }

    public void insertAt(int index, T item) {
        checkIndex(index);

        if (isEmpty()) {
            head = tail = new Node<>(item);
        } else {
            Node<T> trav = head;
            for (int i = 0; i < index; i++) {
                trav = trav.next();
            }

            Node<T> newNode;
            // [If]: Adding new element to tail scenario.
            // If trav is null, this means adding new node
            // to the end of list. So new tail is new node.
            //
            // [Else If]: Adding new element to head scenario.
            // If previous node of current node is null,
            // this means, previous node is head.
            // So new head will be new node.
            //
            // [Else]: Adding new element between the nodes scenario.
            if (trav == null) {
                newNode = new Node<>(item, tail, null);
                tail.setNext(newNode);
                tail = newNode;
            } else if (trav.prev() == null) {
                newNode = new Node<>(item, null, trav);
                head = newNode;
            } else {
                newNode = new Node<>(item, trav.prev(), trav);
                trav.prev().setNext(newNode);
                trav.setPrev(newNode);
            }
        }
        size++;
    }

    public int findIndex(T item) throws NoSuchElementException {
        Iterator<T> iterator = iterator();
        int index = 0;

        while (iterator.hasNext()) {
            // To searching null value of data in nodes checking is value null.
            // Otherwise check equality of data
            T data = iterator.next();
            if (item == null) {
                if (data == null) {
                    return index;
                }
            } else if (item.equals(data)) {
                return index;
            }
            index++;
        }
        throw new NoSuchElementException("Item couldn't be found.");
    }

    public T getAt(int index) throws IndexOutOfBoundsException {
        checkIndex(index);

        Node<T> trav = head;
        for (int i = 0; i < index; i++) {
            trav = trav.next();
        }
        return trav.data();
    }

    public void setAt(int index, T item) throws IndexOutOfBoundsException {
        checkIndex(index);

        Node<T> trav = head;
        for (int i = 0; i < index; i++) {
            trav = trav.next();
        }
        trav.setData(item);
    }

    public void remove(T item) throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException("List is empty.");
        }
        Node<T> trav = head;
        while (trav != null) {
            // To searching null value of data in nodes checking is value null.
            // Otherwise check equality of data
            if (item == null) {
                if (trav.data() == null) {
                    removeNode(trav);
                    return;
                }
            } else if (item.equals(trav.data())) {
                removeNode(trav);
                return;
            }
        }
        throw new NoSuchElementException("Item couldn't be found.");
    }

    public T removeAt(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        Node<T> trav = head;
        for (int i = 0; i < index; i++) {
            trav = trav.next();
        }
        return removeNode(trav);
    }

    /*
     * This function calls removeAt function.
     * Since target element is first element, complexity to reach first element
     * is O(1).
     * So complexity is O(1)
     */
    public T removeFirst() throws NoSuchElementException {
        return removeAt(0);
    }

    /*
     * This function make remove operation of last element by using tail of the
     * list.
     * Since we can reach directly to the tail element, complexity is O(1)
     */
    public T removeLast() throws NoSuchElementException {
        if (tail == null) {
            throw new NoSuchElementException("List is empty.");
        }

        return removeNode(tail);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> trav = head;

            @Override
            public boolean hasNext() {
                if (trav.data() != null) {
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                T data = trav.data();
                trav = trav.next();
                return data;
            }

        };
    }

    private class Node<T> {
        private Node<T> prev, next;
        private T data;

        private Node() {
        }

        public Node(T data) {
            this(data, null, null);
        }

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public Node<T> next() {
            return next;
        }

        public Node<T> prev() {
            return prev;
        }

        public T data() {
            return data;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0) {
            throw new IndexOutOfBoundsException(
                    "Index value can not be below 0. You've entered index: " + index);
        }
        if (index > size) {
            throw new IndexOutOfBoundsException(
                    "List size is " + size + " . You've tried to reach index: " + index);
        }
    }

    private T removeNode(Node<T> node) {
        T data = node.data();

        // Checking is deleting head scenario:
        // If to be deleted node is head,
        // then new head will be next node of to be deleted node.
        if (node.prev() != null) {
            node.prev().setNext(node.next());
        } else {
            head = node.next();
        }

        // Checking is deleting tail scenario:
        // If to be deleted node is tail,
        // then new tail will be previous node of to be deleted node.
        if (node.next() != null) {
            node.next().setPrev(node.prev());
        } else {
            tail = node.prev();
        }
        node.setData(null);
        node.setNext(null);
        node.setPrev(null);
        size--;
        return data;
    }
}
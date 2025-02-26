package com.omeraytekin.data_structures;

import java.util.NoSuchElementException;

public class LinkedQueue<T> extends AbstractQueue<T> {
    List<T> queue;

    public LinkedQueue() {
        queue = new LinkedList<>();
    }

    public LinkedQueue(T[] items) {
        this();
        for (int i = 0; i < items.length; i++) {
            offer(items[i]);
        }
    }

    /*
     * Since LinkedList#insert method runs in amortized O(1) time,
     * complexity of this method is amortized O(1).
     */
    public void offer(T item) {
        super.offer(item);
    }

    /*
     * Since LinkedList#removeFirst method runs in amortized O(1) time,
     * complexity of this method is amortized O(1).
     */
    public T poll() throws NoSuchElementException {
        return super.poll();
    }

    /*
     * Since LinkedList#getFirst method runs in amortized O(1) time,
     * complexity of this method is amortized O(1).
     */
    public T peek() throws NoSuchElementException {
        return super.peek();
    }
}
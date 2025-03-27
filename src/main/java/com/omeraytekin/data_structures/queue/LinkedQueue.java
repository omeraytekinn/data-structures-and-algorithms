package com.omeraytekin.data_structures.queue;

import java.util.NoSuchElementException;

import com.omeraytekin.data_structures.list.LinkedList;

public class LinkedQueue<T> extends AbstractQueue<T> {
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
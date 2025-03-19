package com.omeraytekin.data_structures.queue;

import java.util.NoSuchElementException;

import com.omeraytekin.data_structures.list.ArrayList;
import com.omeraytekin.data_structures.list.List;

public class ArrayQueue<T> extends AbstractQueue<T> {
    List<T> queue;

    public ArrayQueue() {
        queue = new ArrayList<>();
    }

    public ArrayQueue(T[] items) {
        this();
        for (int i = 0; i < items.length; i++) {
            offer(items[i]);
        }
    }

    /*
     * Since ArrayList#insert method runs in amortized O(1) time,
     * complexity of this method is amortized O(1).
     */
    public void offer(T item) {
        super.offer(item);
    }

    /*
     * Since ArrayList#removeFirst method runs in amortized O(n) time,
     * complexity of this method is amortized O(n).
     */
    public T poll() throws NoSuchElementException {
        return super.poll();
    }

    /*
     * Since ArrayList#getFirst method runs in amortized O(1) time,
     * complexity of this method is amortized O(1).
     */
    public T peek() throws NoSuchElementException {
        return super.peek();
    }
}
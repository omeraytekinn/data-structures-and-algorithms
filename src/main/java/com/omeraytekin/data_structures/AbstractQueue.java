package com.omeraytekin.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractQueue<T> implements Queue<T> {
    List<T> queue;
    int size;

    public void offer(T item) {
        queue.insert(item);
    }

    public T poll() throws NoSuchElementException {
        return queue.removeFirst();
    }

    public T peek() throws NoSuchElementException {
        return queue.getFirst();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return queue.size();
    }

    public Iterator<T> iterator() {
        return queue.iterator();
    }
}

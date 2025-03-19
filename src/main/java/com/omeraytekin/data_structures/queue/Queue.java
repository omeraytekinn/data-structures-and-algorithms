package com.omeraytekin.data_structures.queue;

import java.util.NoSuchElementException;

public interface Queue<T> extends Iterable<T> {
    public void offer(T item);

    public T poll() throws NoSuchElementException;

    public T peek() throws NoSuchElementException;

    public boolean isEmpty();

    public int size();
}

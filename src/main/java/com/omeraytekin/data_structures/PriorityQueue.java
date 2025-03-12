package com.omeraytekin.data_structures;

import java.util.NoSuchElementException;

public interface PriorityQueue<T extends Comparable<T>> {
    public void insert(T item);

    public T peek() throws NoSuchElementException;

    public T pop() throws NoSuchElementException;

    public void remove(T item) throws NoSuchElementException;

    public void clear();

    public int size();

    public boolean isEmpty();
}

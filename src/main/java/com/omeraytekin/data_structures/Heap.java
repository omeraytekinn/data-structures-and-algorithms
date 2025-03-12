package com.omeraytekin.data_structures;

import java.util.NoSuchElementException;

public interface Heap<T extends Comparable<T>> {
    public void insert(T item);

    public T getFirst() throws NoSuchElementException;

    public boolean contains(T item);

    public void remove(T item) throws NoSuchElementException;

    public void clear();

    public boolean checkHeapProperty();

    public void changeHeapType(HeapType type);

    public HeapType getType();

    public void printHeap();

    public int size();

    public boolean isEmpty();
}

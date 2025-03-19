package com.omeraytekin.data_structures.list;

import java.util.NoSuchElementException;

public interface List<T> extends Iterable<T> {
    public void insert(T item);

    public void insertAt(int index, T item);

    public int findIndex(T item) throws NoSuchElementException;

    public boolean exists(T item);

    public T getAt(int index) throws IndexOutOfBoundsException;

    public T getFirst() throws NoSuchElementException;

    public T getLast() throws NoSuchElementException;

    public void setAt(int index, T item) throws IndexOutOfBoundsException;

    public void remove(T item) throws NoSuchElementException;

    public T removeAt(int index) throws IndexOutOfBoundsException;

    public T removeFirst() throws NoSuchElementException;

    public T removeLast() throws NoSuchElementException;

    public void clear();

    public boolean isEmpty();

    public int size();
}
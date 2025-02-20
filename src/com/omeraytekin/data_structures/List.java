package com.omeraytekin.data_structures;

import java.util.NoSuchElementException;

public interface List<T> extends Iterable<T> {
    public void insert(T item);

    public void insertAt(int index, T item);

    public int findIndex(T item) throws NoSuchElementException;

    public T getAt(int index) throws IndexOutOfBoundsException;

    public void setAt(int index, T item) throws IndexOutOfBoundsException;

    public void remove(T item) throws NoSuchElementException;

    public T removeAt(int index) throws IndexOutOfBoundsException;

    public boolean isEmpty();

    public int size();
}
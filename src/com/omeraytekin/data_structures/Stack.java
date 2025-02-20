package com.omeraytekin.data_structures;

import java.util.EmptyStackException;

public interface Stack<T> extends Iterable<T> {
    public void push(T item);

    public T pop() throws EmptyStackException;

    public T peek() throws EmptyStackException;

    public boolean isEmpty();

    public int size();
}

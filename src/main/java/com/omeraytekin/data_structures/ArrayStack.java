package com.omeraytekin.data_structures;

import java.util.EmptyStackException;
import java.util.Iterator;

public class ArrayStack<T> implements Stack<T> {
    List<T> stack;

    public ArrayStack() {
        stack = new ArrayList<>();
    }

    public ArrayStack(T[] items) {
        this();
        // Pushing items in reverse order to keep pop order.
        for (int i = items.length - 1; i >= 0; i--) {
            push(items[i]);
        }
    }

    public void push(T item) {
        stack.insert(item);
    }

    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return stack.removeAt(size() - 1);
    }

    public T peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return stack.getAt(size() - 1);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return stack.size();
    }

    public Iterator<T> iterator() {
        return stack.iterator();
    }

}

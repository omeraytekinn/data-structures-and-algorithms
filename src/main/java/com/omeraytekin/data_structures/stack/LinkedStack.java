package com.omeraytekin.data_structures.stack;

import java.util.EmptyStackException;
import java.util.Iterator;

import com.omeraytekin.data_structures.list.LinkedList;
import com.omeraytekin.data_structures.list.List;

public class LinkedStack<T> implements Stack<T> {
    List<T> stack;

    public LinkedStack() {
        stack = new LinkedList<>();
    }

    public LinkedStack(T[] items) {
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

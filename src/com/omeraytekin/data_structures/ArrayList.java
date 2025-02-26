package com.omeraytekin.data_structures;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class ArrayList<T> implements List<T> {
    private int size;
    private int maxSize;
    private float growFactor;
    private Object[] items;

    public ArrayList() {
        this(32, 2);
    }

    public ArrayList(int initSize) {
        this(initSize, 2);
    }

    public ArrayList(int initSize, float growFactor) {
        if (growFactor <= 1.0) {
            growFactor = 2;
        }
        items = new Object[initSize];
        size = 0;
        maxSize = initSize;
        this.growFactor = growFactor;
    }

    public ArrayList(T[] items) {
        this(items.length, 2);
        for (int i = 0; i < items.length; i++) {
            this.insert(items[i]);
        }
    }

    public void insert(T item) {
        if (size == maxSize) {
            growItems();
        }
        items[size] = item;
        size++;
    }

    /*
     * This function calls insertAt function with worst case.
     * All elements will be shifted to add element to first index.
     * So complexity is O(n)
     */
    public void insertFirst(T item) {
        insertAt(0, item);
    }

    public void insertAt(int index, T item) {
        checkIndex(index);
        if (size == maxSize) {
            growItems();
        }
        for (int i = size; i > index; i++) {
            items[i] = items[i - 1];
        }
        items[index] = item;
        size++;
    }

    public int findIndex(T item) throws NoSuchElementException {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)) {
                return i;
            }
        }

        throw new NoSuchElementException("Item couldn't be found.");
    }

    public T getAt(int index) throws IndexOutOfBoundsException {
        checkIndex(index);

        return (T) items[index];
    }

    public void setAt(int index, T item) throws IndexOutOfBoundsException {
        checkIndex(index);

        items[index] = item;
    }

    public void remove(T item) throws NoSuchElementException {
        int index = findIndex(item);

        removeAt(index);
    }

    public T removeAt(int index) throws IndexOutOfBoundsException {
        checkIndex(index);

        T item = getAt(index);
        for (int i = index; i < size; i++) {
            items[i] = items[i + 1];
        }
        size--;
        return item;
    }

    /*
     * This function calls removeAt function with worst case.
     * All elements will be shifted to remove first element.
     * So complexity is O(n)
     */
    public T removeFirst() throws NoSuchElementException {
        try {
            return removeAt(0);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException("List is empty.");
        }
    }

    /*
     * This function calls insert function.
     * No need to shift elements for removing last element.
     * So complexity is O(1)
     */
    public T removeLast() throws NoSuchElementException {
        try {
            return removeAt(size - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException("List is empty.");
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return (T) items[index++];
            }
        };
    }

    private void growItems() {
        maxSize = (int) Math.floor(maxSize * growFactor) + 1;
        Object[] newItems = new Object[maxSize];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0) {
            throw new IndexOutOfBoundsException(
                    "Index value can not be below 0. You've entered index: " + index);
        }
        if (index > size) {
            throw new IndexOutOfBoundsException(
                    "List size is " + size + " . You've tried to reach index: " + index);
        }
    }
}
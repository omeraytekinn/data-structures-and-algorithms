package com.omeraytekin.data_structures;

import java.util.NoSuchElementException;

public class MinHeap<T extends Comparable<T>> {
    private List<T> items;

    MinHeap(int initSize, float growingFactor) {
        if (initSize <= 0) {
            throw new IllegalArgumentException("initSize must be greater than zero");
        }
        if (growingFactor <= 1) {
            throw new IllegalArgumentException("growingFactor must be greater than one");
        }
        this.items = new ArrayList<>(initSize, growingFactor);
    }

    public void insert(T item) {
        items.insert(item);
        swim(size() - 1);
    }

    public void remove(T item) throws NoSuchElementException {
        int index = items.findIndex(item);
        if (index == size() - 1) {
            items.removeLast();
        } else {
            items.setAt(index, items.getLast());
            items.removeLast();
            sinkAndSwim(index);
        }
    }

    public boolean checkHeapProperty() {
        for (int i = 2; i < size(); i++) {
            if (compareParent(i) > 0) {
                return false;
            }
        }
        return true;
    }

    public void printHeap() {
        System.out.println("size: " + size());
        System.out.println(items);
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    private int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int rightChildIndex(int index) {
        return 2 * index + 2;
    }

    private int parentIndex(int index) {
        return (int) Math.floor((index - 1) / 2);
    }

    private void sinkAndSwim(int index) {
        sink(index);
        swim(index);
    }

    private void sink(int index) {
        T item = items.getAt(index);
        int leftIndex = leftChildIndex(index);
        int rightIndex = rightChildIndex(index);
        T leftChild = leftIndex < size() ? items.getAt(leftIndex) : null;
        T rightChild = rightIndex < size() ? items.getAt(rightIndex) : null;
        if (leftChild != null && item.compareTo(leftChild) > 0) {
            swap(index, leftIndex);
            index = leftIndex;
            sink(index);
        } else if (rightChild != null && item.compareTo(rightChild) > 0) {
            swap(index, rightIndex);
            index = rightIndex;
            sink(index);
        }
        return;
    }

    private void swim(int index) {
        while (index > 0 && compareParent(index) > 0) {
            int parent = parentIndex(index);
            swap(parent, index);
            index = parentIndex(index);
        }
    }

    private void swap(int index1, int index2) {
        T temp = items.getAt(index1);
        items.setAt(index1, items.getAt(index2));
        items.setAt(index2, temp);
        temp = null;
    }

    private int compareParent(int index) {
        T item = items.getAt(index);
        T parent = items.getAt(parentIndex(index));
        return parent.compareTo(item);
    }
}

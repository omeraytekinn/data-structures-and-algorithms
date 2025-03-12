package com.omeraytekin.data_structures;

import java.util.NoSuchElementException;

public class BinaryHeap<T extends Comparable<T>> implements Heap<T> {
    private List<T> items;
    private HeapType type;
    private float growingFactor;

    BinaryHeap() {
        this(32);
    }

    BinaryHeap(int initSize) {
        this(HeapType.MIN_HEAP, initSize, 2);
    }

    BinaryHeap(int initSize, float growingFactor) {
        this(HeapType.MIN_HEAP, initSize, growingFactor);
    }

    BinaryHeap(HeapType type, int initSize, float growingFactor) {
        if (initSize <= 0) {
            throw new IllegalArgumentException("initSize must be greater than zero");
        }
        if (growingFactor <= 1) {
            throw new IllegalArgumentException("growingFactor must be greater than one");
        }
        items = new ArrayList<>(initSize, growingFactor);
        this.type = type;
        this.growingFactor = growingFactor;
    }

    @Override
    public void insert(T item) {
        items.insert(item);
        swim(size() - 1);
    }

    @Override
    public T getFirst() {
        return items.getFirst();
    }

    @Override
    public boolean exists(T item) {
        try {
            items.findIndex(item);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
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

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public boolean checkHeapProperty() {
        for (int i = 2; i < size(); i++) {
            if (compareParent(i) > 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void changeHeapType(HeapType type) {
        if (!this.type.equals(type)) {
            this.type = type;
            List<T> tempItems = items;
            items = new ArrayList<>(items.size(), growingFactor);
            tempItems.forEach((item) -> {
                insert(item);
            });
            tempItems.clear();
            tempItems = null;
        }
    }

    @Override
    public HeapType getType() {
        return type;
    }

    @Override
    public void printHeap() {
        System.out.println(items);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
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
        if (leftChild != null && compare(item, leftChild) > 0) {
            swap(index, leftIndex);
            index = leftIndex;
            sink(index);
        } else if (rightChild != null && compare(item, rightChild) > 0) {
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
        return compare(parent, item);
    }

    private int compare(T item1, T item2) {
        if (HeapType.MIN_HEAP.equals(type)) {
            return item1.compareTo(item2);
        } else {
            return item2.compareTo(item1);
        }
    }
}

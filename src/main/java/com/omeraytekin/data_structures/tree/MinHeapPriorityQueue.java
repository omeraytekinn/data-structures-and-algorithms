package com.omeraytekin.data_structures.tree;

import java.util.NoSuchElementException;

public class MinHeapPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {
    Heap<T> heap;

    MinHeapPriorityQueue() {
        this(32);
    }

    MinHeapPriorityQueue(int initSize) {
        heap = new BinaryHeap<>(HeapType.MIN_HEAP, initSize, 2);
    }

    public void insert(T item) {
        heap.insert(item);
    }

    public T peek() throws NoSuchElementException {
        try {
            return heap.getFirst();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Priority Queue is empty");
        }
    }

    public T pop() throws NoSuchElementException {
        try {
            T item = heap.getFirst();
            heap.remove(item);
            return item;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Priority Queue is empty");
        }
    }

    public void remove(T item) throws NoSuchElementException {
        heap.remove(item);
    }

    public void clear() {
        heap.clear();
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}

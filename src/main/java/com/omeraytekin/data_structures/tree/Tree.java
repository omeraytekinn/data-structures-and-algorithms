package com.omeraytekin.data_structures.tree;

public interface Tree<T extends Comparable<T>> {
    boolean isEmpty();

    int size();

    void clear();

    boolean contains(T data);
}

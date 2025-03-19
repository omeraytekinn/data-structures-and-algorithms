package com.omeraytekin.data_structures.tree;

import java.util.NoSuchElementException;

public interface MultiTree<T extends Comparable<T>> extends Tree<T> {
    void insert(T data);

    void remove(T data) throws NoSuchElementException;
}
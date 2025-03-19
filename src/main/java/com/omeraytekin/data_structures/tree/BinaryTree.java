package com.omeraytekin.data_structures.tree;

import java.util.NoSuchElementException;

public interface BinaryTree<T extends Comparable<T>> extends Tree<T> {
    void insert(T data);

    void remove(T data) throws NoSuchElementException;

    void traverseInOrder();

    void traversePreOrder();

    void traversePostOrder();
}
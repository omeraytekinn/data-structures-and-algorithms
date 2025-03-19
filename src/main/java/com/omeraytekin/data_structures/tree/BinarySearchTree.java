package com.omeraytekin.data_structures.tree;

public class BinarySearchTree<T extends Comparable<T>> implements BinaryTree<T> {
    private BinaryTreeNode<T> root;
    private int size;

    public BinarySearchTree() {
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(T data) {
        BinaryTreeNode<T> trav = root;
        while (trav != null) {
            int cmp = data.compareTo(trav.getData());
            if (cmp == 0) {
                return true;
            } else if (cmp > 0) {
                trav = root.getRightChild();
            } else if (cmp < 0) {
                trav = root.getLeftChild();
            }
        }
        return false;
    }

    @Override
    public void insert(T data) {
        BinaryTreeNode<T> trav = root;
        if (trav == null) {
            root = new BinaryTreeNode<>(data);
        } else {
            while (trav != null) {
                if (trav.getData().compareTo(data) < 0) {
                    if (trav.getLeftChild() == null) {
                        trav.setLeftChild(new BinaryTreeNode<>(data));
                        break;
                    }
                } else {
                    if (trav.getRightChild() == null) {
                        trav.setLeftChild(new BinaryTreeNode<>(data));
                        break;
                    }
                }
                trav = trav.getLeftChild();
            }
        }
    }

    @Override
    public void remove(T data) {
        BinaryTreeNode<T> current = root;
        BinaryTreeNode<T> parent = root;
        while (current != null) {
            int cmp = data.compareTo(current.getData());
            if (cmp == 0) {
                break;
            } else if (cmp > 0) {
                parent = current;
                current = root.getRightChild();
            } else if (cmp < 0) {
                parent = current;
                current = root.getLeftChild();
            }
        }

        if (current != null) {
            current.setData(null);
            if (current.getLeftChild() != null && current.getRightChild() != null) {
                BinaryTreeNode<T> leftMax = current.getLeftChild();
                BinaryTreeNode<T> leftMaxParent = current;
                while (leftMax.getRightChild() != null) {
                    leftMaxParent = leftMax;
                    leftMax = leftMax.getRightChild();
                }
                current.setData(leftMax.getData());
                current = leftMax;
                parent = leftMaxParent;
            }

            if (current.getLeftChild() == null && current.getRightChild() == null) {
                if (current == root) {
                    root = null;
                } else if (current == parent.getLeftChild()) {
                    parent.setLeftChild(null);
                } else {
                    parent.setRightChild(null);
                }
            } else if (current.getLeftChild() == null) {
                if (current == root) {
                    root = current.getRightChild();
                } else if (current == parent.getLeftChild()) {
                    parent.setLeftChild(current.getRightChild());
                } else {
                    parent.setRightChild(current.getRightChild());
                }
            } else if (current.getRightChild() == null) {
                if (current == root) {
                    root = current.getLeftChild();
                } else if (current == parent.getLeftChild()) {
                    parent.setLeftChild(current.getLeftChild());
                } else {
                    parent.setRightChild(current.getLeftChild());
                }
            }
        }
    }

    @Override
    public void traverseInOrder() {

    }

    @Override
    public void traversePreOrder() {

    }

    @Override
    public void traversePostOrder() {

    }
}

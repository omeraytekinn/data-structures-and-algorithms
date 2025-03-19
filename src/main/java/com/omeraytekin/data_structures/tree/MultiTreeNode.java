package com.omeraytekin.data_structures.tree;

import java.util.NoSuchElementException;

import com.omeraytekin.data_structures.list.ArrayList;
import com.omeraytekin.data_structures.list.List;

public class MultiTreeNode<T extends Comparable<T>> implements TreeNode<T> {
    private T data;
    private List<MultiTreeNode<T>> children;

    public MultiTreeNode(T data) {
        this.data = data;
        children = new ArrayList<>();
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    public List<MultiTreeNode<T>> getChildren() {
        return children;
    }

    public MultiTreeNode<T> getChildrenAt(int index) {
        return children.getAt(index);
    }

    public void addChild(MultiTreeNode<T> node) {
        children.insert(node);
    }

    public void removeChild(MultiTreeNode<T> node) throws NoSuchElementException {
        children.remove(node);
    }

    public void clearChildren() {
        children.clear();
    }
}

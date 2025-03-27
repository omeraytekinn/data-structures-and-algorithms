package com.omeraytekin.data_structures.tree;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.omeraytekin.data_structures.queue.ArrayQueue;
import com.omeraytekin.data_structures.queue.Queue;
import com.omeraytekin.data_structures.stack.ArrayStack;
import com.omeraytekin.data_structures.stack.Stack;;

public class BinarySearchTree<T extends Comparable<T>> implements BinaryTree<T> {
    private BinaryTreeNode<T> root;
    private int size;

    public BinarySearchTree() {
        size = 0;
    }

    @Override
    public void insert(T data) {
        BinaryTreeNode<T> trav = root;
        if (trav == null) {
            root = new BinaryTreeNode<>(data);
        } else {
            while (trav != null) {
                if (trav.getData().compareTo(data) > 0) {
                    if (trav.getLeftChild() == null) {
                        trav.setLeftChild(new BinaryTreeNode<>(data));
                        break;
                    }
                    trav = trav.getLeftChild();
                } else {
                    if (trav.getRightChild() == null) {
                        trav.setRightChild(new BinaryTreeNode<>(data));
                        break;
                    }
                    trav = trav.getRightChild();
                }
            }
        }
        size++;
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
                current = current.getRightChild();
            } else if (cmp < 0) {
                parent = current;
                current = current.getLeftChild();
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
            size--;
        }
    }

    @Override
    public Iterator<T> traverse(BinaryTreeTraversalOrder order) {
        switch (order) {
            case PRE_ORDER:
                return preOrderTraversal();
            case IN_ORDER:
                return inOrderTraversal();
            case POST_ORDER:
                return postOrderTraversal();
            case LEVEL_ORDER:
                return levelOrderTraversal();
            default:
                return null;
        }
    }

    private Iterator<T> preOrderTraversal() {
        return new Iterator<T>() {
            private Stack<BinaryTreeNode<T>> stack = new ArrayStack<>();
            {
                if (root != null) {
                    stack.push(root);
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public T next() {
                BinaryTreeNode<T> node = stack.pop();
                if (node.getRightChild() != null) {
                    stack.push(node.getRightChild());
                }
                if (node.getLeftChild() != null) {
                    stack.push(node.getLeftChild());
                }
                return node.getData();
            }
        };
    }

    private Iterator<T> inOrderTraversal() {
        return new Iterator<T>() {
            private Stack<BinaryTreeNode<T>> stack = new ArrayStack<>();
            {
                BinaryTreeNode<T> trav = root;
                while (trav != null) {
                    stack.push(trav);
                    trav = trav.getLeftChild();
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public T next() throws NoSuchElementException {
                BinaryTreeNode<T> node;
                try {
                    node = stack.pop();
                } catch (EmptyStackException e) {
                    throw new NoSuchElementException("There are no elements left in the traversal");
                }
                BinaryTreeNode<T> trav = node.getRightChild();
                while (trav != null) {
                    stack.push(trav);
                    trav = trav.getLeftChild();
                }
                return node.getData();
            }
        };
    }

    private Iterator<T> postOrderTraversal() {
        return new Iterator<T>() {
            private Stack<BinaryTreeNode<T>> stack = new ArrayStack<>();
            private BinaryTreeNode<T> lastVisited;
            {
                BinaryTreeNode<T> trav = root;
                while (trav != null) {
                    stack.push(trav);
                    trav = trav.getLeftChild();
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public T next() throws NoSuchElementException {
                while (!stack.isEmpty()) {
                    BinaryTreeNode<T> node = stack.peek();
                    BinaryTreeNode<T> rightChild = node.getRightChild();

                    if (rightChild != null && rightChild != lastVisited) {
                        BinaryTreeNode<T> trav = rightChild;
                        while (trav != null) {
                            stack.push(trav);
                            trav = trav.getLeftChild();
                        }
                    } else {
                        lastVisited = stack.pop();
                        return lastVisited.getData();
                    }
                }
                throw new NoSuchElementException("There are no elements left in the traversal");
            }
        };
    }

    private Iterator<T> levelOrderTraversal() {
        return new Iterator<T>() {
            private Queue<BinaryTreeNode<T>> queue = new ArrayQueue<>();
            {
                if (root != null) {
                    queue.offer(root);
                }
            }

            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public T next() throws NoSuchElementException {
                if (queue.isEmpty()) {
                    throw new NoSuchElementException("There are no elements left in the traversal");
                }
                BinaryTreeNode<T> node = queue.poll();
                if (node.getLeftChild() != null) {
                    queue.offer(node.getLeftChild());
                }
                if (node.getRightChild() != null) {
                    queue.offer(node.getRightChild());
                }
                return node.getData();
            }
        };
    }

    @Override
    public boolean contains(T data) {
        BinaryTreeNode<T> trav = root;
        while (trav != null) {
            int cmp = data.compareTo(trav.getData());
            if (cmp == 0) {
                return true;
            } else if (cmp > 0) {
                trav = trav.getRightChild();
            } else if (cmp < 0) {
                trav = trav.getLeftChild();
            }
        }
        return false;
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
        Queue<BinaryTreeNode<T>> queue = new ArrayQueue<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode<T> node = queue.poll();
            if (node.getLeftChild() != null) {
                queue.offer(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                queue.offer(node.getRightChild());
            }
            node.setData(null);
            node.setLeftChild(null);
            node.setRightChild(null);
        }
        root = null;
        size = 0;
    }
}

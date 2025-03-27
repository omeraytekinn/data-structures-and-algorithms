package com.omeraytekin.data_structures.tree;

import java.util.Iterator;

public class BinarySearchTreeSample {
    public static void main(String[] args) {
        BinaryTree<Integer> bst = new BinarySearchTree<>();
        Integer[] elements = { 15, 13, 20, 10, 14, 18, 25, 8, 30, 4, 9 };

        System.out.println("[Elements]");
        for (Integer element : elements) {
            bst.insert(element);
            System.out.print(element + ", ");
        }
        System.out.println("\n");

        Iterator<Integer> preOrderIterator = bst.traverse(BinaryTreeTraversalOrder.PRE_ORDER);
        System.out.println("[PRE_ORDER]");
        while (preOrderIterator.hasNext()) {
            System.out.print(preOrderIterator.next() + ", ");
        }
        System.out.println("\n");

        Iterator<Integer> inOrderIterator = bst.traverse(BinaryTreeTraversalOrder.IN_ORDER);
        System.out.println("[IN_ORDER]");
        while (inOrderIterator.hasNext()) {
            System.out.print(inOrderIterator.next() + ", ");
        }
        System.out.println("\n");

        Iterator<Integer> postOrderIterator = bst.traverse(BinaryTreeTraversalOrder.POST_ORDER);
        System.out.println("[POST_ORDER]");
        while (postOrderIterator.hasNext()) {
            System.out.print(postOrderIterator.next() + ", ");
        }
        System.out.println("\n");

        Iterator<Integer> levelOrderIterator = bst.traverse(BinaryTreeTraversalOrder.LEVEL_ORDER);
        System.out.println("[LEVEL_ORDER]");
        while (levelOrderIterator.hasNext()) {
            System.out.print(levelOrderIterator.next() + ", ");
        }
        System.out.println("\n");
    }
}

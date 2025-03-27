package com.omeraytekin.data_structures.tree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> bst;
    private final Integer[] elements = { 15, 13, 20, 10, 14, 18, 25, 8, 30, 4, 9 };

    private static Object[] traverseOrders() {
        return new Object[] {
                new Object[] { BinaryTreeTraversalOrder.PRE_ORDER,
                        new Integer[] { 15, 13, 10, 8, 4, 9, 14, 20, 18, 25, 30 } },
                new Object[] { BinaryTreeTraversalOrder.IN_ORDER,
                        new Integer[] { 4, 8, 9, 10, 13, 14, 15, 18, 20, 25, 30 } },
                new Object[] { BinaryTreeTraversalOrder.POST_ORDER,
                        new Integer[] { 4, 9, 8, 10, 14, 13, 18, 30, 25, 20, 15 } },
                new Object[] { BinaryTreeTraversalOrder.LEVEL_ORDER,
                        new Integer[] { 15, 13, 20, 10, 14, 18, 25, 8, 30, 4, 9 } }
        };
    }

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree<>();
        for (Integer element : elements) {
            bst.insert(element);
        }
    }

    @Test
    void testInsert() {
        assertEquals(elements.length, bst.size());
        assertTrue(bst.contains(15));
        assertTrue(bst.contains(30));
        assertFalse(bst.contains(100));
    }

    @Test
    void testRemoveLeafNode() {
        bst.remove(9);
        assertEquals(elements.length - 1, bst.size());
        assertFalse(bst.contains(9));
    }

    @Test
    void testRemoveNodeWithOneChild() {
        bst.remove(8);
        assertEquals(elements.length - 1, bst.size());
        assertFalse(bst.contains(8));
    }

    @Test
    void testRemoveNodeWithTwoChildren() {
        bst.remove(13);
        assertEquals(elements.length - 1, bst.size());
        assertFalse(bst.contains(13));
    }

    @ParameterizedTest
    @MethodSource("traverseOrders")
    void testTraversals(BinaryTreeTraversalOrder order, Integer[] expectedResult) {
        Iterator<Integer> iter = bst.traverse(order);
        Integer[] result = new Integer[expectedResult.length];
        int i = 0;
        while (iter.hasNext()) {
            result[i] = iter.next();
            i++;
        }
        assertTrue(Arrays.equals(expectedResult, result));
    }

    @Test
    void testIsEmpty() {
        assertFalse(bst.isEmpty());
        bst.clear();
        assertTrue(bst.isEmpty());
    }
}

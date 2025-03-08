package com.omeraytekin.data_structures;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MinHeapTest {
    private MinHeap<Integer> heap;

    @BeforeEach
    void setUp() {
        heap = new MinHeap<>(5, 2);
    }

    @Test
    void testValidConstructor() {
        MinHeap<Integer> heap = new MinHeap<>(10, 2);
        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testConstructorWithInvalidInitSize() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new MinHeap<>(0, 2));
        assertEquals(exception.getMessage(), "initSize must be greater than zero");
    }

    @Test
    void testConstructorWithInvalidGrowingFactor() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new MinHeap<>(10, 1));
        assertEquals(exception.getMessage(), "growingFactor must be greater than one");
    }

    @Test
    void testInsertMaintainsHeapProperty() {
        heap.insert(5);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);
        assertTrue(heap.checkHeapProperty());
    }

    @Test
    void testInsertHeapGrows() {
        MinHeap<Integer> heap = new MinHeap<>(5, 2);
        for (int i = 0; i < 6; i++) {
            heap.insert(i);
        }
        assertTrue(heap.size() == 6);
    }

    @Test
    void testRemoveFirstItemMaintainsHeapProperty() {
        heap.insert(5);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);
        heap.remove(1);
        assertTrue(heap.checkHeapProperty());
    }

    @Test
    void testRemoveLastItemMaintainsHeapProperty() {
        heap.insert(5);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);
        heap.remove(3);
        assertTrue(heap.checkHeapProperty());
    }

    @Test
    void testRemoveMiddleItemMaintainsHeapProperty() {
        heap.insert(5);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);
        heap.remove(2);
        assertTrue(heap.checkHeapProperty());
    }

    @Test
    void testRemoveItemWithNoChildMaintainsHeapProperty() {
        heap.insert(5);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);
        heap.remove(4);
        assertTrue(heap.checkHeapProperty());
    }
}

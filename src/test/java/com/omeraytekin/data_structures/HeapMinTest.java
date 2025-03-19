package com.omeraytekin.data_structures;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.omeraytekin.data_structures.tree.BinaryHeap;
import com.omeraytekin.data_structures.tree.Heap;
import com.omeraytekin.data_structures.tree.HeapType;

public class HeapMinTest {
    private Heap<Integer> heap;

    @BeforeEach
    void setUp() {
        heap = new BinaryHeap<>(HeapType.MIN_HEAP, 5, 2);
    }

    @Test
    void testValidConstructor() {
        Heap<Integer> heap = new BinaryHeap<>(HeapType.MIN_HEAP, 10, 2);
        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testConstructorWithInvalidInitSize() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new BinaryHeap<>(HeapType.MIN_HEAP, 0, 2));
        assertEquals(exception.getMessage(), "initSize must be greater than zero");
    }

    @Test
    void testConstructorWithInvalidGrowingFactor() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new BinaryHeap<>(HeapType.MIN_HEAP, 10, 1));
        assertEquals(exception.getMessage(), "growingFactor must be greater than one");
    }

    @Test
    void testInsertMaintainsHeapProperty() {
        for (int i = 0; i < 5; i++) {
            heap.insert(5 - i);
        }
        assertTrue(heap.checkHeapProperty());
    }

    @Test
    void testInsertHeapGrows() {
        Heap<Integer> heap = new BinaryHeap<>(HeapType.MIN_HEAP, 5, 2);
        for (int i = 0; i < 6; i++) {
            heap.insert(i);
        }
        assertTrue(heap.size() == 6);
    }

    @Test
    void testRemoveFirstItemMaintainsHeapProperty() {
        for (int i = 0; i < 5; i++) {
            heap.insert(5 - i);
        }
        heap.remove(1);
        assertTrue(heap.checkHeapProperty());
    }

    @Test
    void testRemoveLastItemMaintainsHeapProperty() {
        for (int i = 0; i < 5; i++) {
            heap.insert(5 - i);
        }
        heap.remove(3);
        assertTrue(heap.checkHeapProperty());
    }

    @Test
    void testRemoveMiddleItemMaintainsHeapProperty() {
        for (int i = 0; i < 5; i++) {
            heap.insert(5 - i);
        }
        heap.remove(2);
        assertTrue(heap.checkHeapProperty());
    }

    @Test
    void testRemoveItemWithNoChildMaintainsHeapProperty() {
        for (int i = 0; i < 5; i++) {
            heap.insert(5 - i);
        }
        heap.remove(4);
        assertTrue(heap.checkHeapProperty());
    }

    @Test
    void testChangeHeapTypeToMaxHeapMaintainsHeapProperty() {
        for (int i = 0; i < 5; i++) {
            heap.insert(5 - i);
        }
        heap.changeHeapType(HeapType.MAX_HEAP);
        assertTrue(heap.checkHeapProperty());
    }
}

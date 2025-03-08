package com.omeraytekin.data_structures;

import java.util.Random;

public class MinHeapSample {
    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>(10, 2);
        Random random = new Random();
        List<Integer> testData = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            int item = random.nextInt(100);
            testData.insert(item);
            heap.insert(item);
        }
        testData.insert(15);
        heap.insert(15);
        System.out.println("[Insertion Order]");
        System.out.println(testData);
        System.out.println("\n[Heap Order]");
        heap.printHeap();
        System.out.println();
        heap.remove(15);
        heap.printHeap();

        heap = new MinHeap<>(5, 2);
        heap.insert(5);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);
        heap.printHeap();
        heap.remove(3);
        heap.printHeap();

    }
}

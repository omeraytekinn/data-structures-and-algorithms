package com.omeraytekin.data_structures;

import java.util.Random;

public class HeapMaxSample {
    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>(HeapType.MAX_HEAP, 10, 2);
        Random random = new Random();
        List<Integer> testData = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            int item = random.nextInt(100);
            testData.insert(item);
            heap.insert(item);
        }
        testData.insert(20);
        heap.insert(20);
        testData.insert(40);
        heap.insert(40);
        testData.insert(60);
        heap.insert(60);
        testData.insert(80);
        heap.insert(80);
        System.out.println("[Insertion Order]");
        System.out.println(testData);
        System.out.println("\n[Heap Order]");
        heap.printHeap();

        heap.remove(20);
        heap.remove(40);
        heap.remove(60);
        heap.remove(80);
        System.out.println("\n[Heap Order After Remove 20,40,60,80]");
        heap.printHeap();

        heap = new Heap<>(HeapType.MAX_HEAP, 5, 2);
        for (int i = 0; i < 5; i++) {
            heap.insert(i + 1);
        }
        System.out.println("");
        heap.printHeap();
        heap.remove(3);
        heap.printHeap();

        heap = new Heap<>(HeapType.MAX_HEAP, 5, 2);
        for (int i = 0; i < 5; i++) {
            heap.insert(i + 1);
        }
        System.out.println("\n[Max Heap Order]");
        heap.printHeap();
        heap.changeHeapType(HeapType.MIN_HEAP);
        System.out.println("\n[Min Heap Order]");
        heap.printHeap();

    }
}

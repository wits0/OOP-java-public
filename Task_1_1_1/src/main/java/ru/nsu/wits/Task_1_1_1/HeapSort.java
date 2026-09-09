package ru.nsu.wits.Task_1_1_1;

/**
 * Heap sort realization.
 * Copyrighting by @wits0 2026 ez so ez task for me omg
 * Dota 2 6200mmr coach!!!
 */
public class HeapSort {
    /**
     * Sorts the given array with using heapsort algorithm.
     *
     * @param array the input array to be sorted
     * return a new sorted array
     */
    public static void sort(int[] array) {
        if (array.length <= 1 ) {
            return;
        }
        int len = array.length;
        for (int index = len / 2 - 1; index >= 0; index--) {
            heapify(array, len, index);
        }
        for (int index = len - 1; index > 0; index--) {
            swap(array, 0, index);
            heapify(array, index, 0);
        }

    }
    /**
     * Heapify return properties of Heap, used swap and heapify (recursively)
     * check that father > then his child's
     * if not, we swap child who bigger than father(use swap)
     * then heapifies the affected subtree
     * @param array  the array we need to heapify
     * @param index the root index of the subtree to heapify
     * @param len the length of our array
     */
    public static void heapify(int[] array,int len,int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int biggest = index;
        if (left<len && array[left] > array[biggest]) {
            biggest = left;
        }
        if (right < len && array[right] > array[biggest]) {
            biggest = right;
        }
        if (biggest != index) {
            swap(array,index,biggest);
            heapify(array,len,biggest);
        }
    }
    private static void swap(int[] array,int left,int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
    private static void heapUp(int[] array,int index) {
        if (index <= 0) {
            return;
            //that mean that index == root -> can't go up
        }
        int parent = (index - 1) / 2;
        if (array[index] > array[parent]) {
            swap(array,index,parent);
            heapUp(array,parent);
        }
    }
}
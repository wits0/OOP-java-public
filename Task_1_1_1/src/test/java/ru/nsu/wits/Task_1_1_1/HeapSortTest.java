package ru.nsu.wits.Task_1_1_1;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class HeapSortTest {

    @Test
    void testSortWithNormalArray() {
        int[] array = {5, 2, 7, 1, 6, 3};
        int[] expected = {1, 2, 3, 5, 6, 7};
        HeapSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testSortWithEmptyArray() {
        int[] array = {};
        HeapSort.sort(array);
        assertArrayEquals(new int[]{}, array);
    }

    @Test
    void testSortWithSingleElement() {
        int[] array = {67};
        HeapSort.sort(array);
        assertArrayEquals(new int[]{67}, array);
    }

    @Test
    void testSortWithSortedArray() {
        int[] array = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        HeapSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testSortWithReverseSortedArray() {
        int[] array = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        HeapSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testSortWithDuplicates() {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5};
        int[] expected = {1, 1, 2, 3, 4, 5, 5, 6, 9};
        HeapSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testSortWithNegativeNumbers() {
        int[] array = {-5, -2, -8, -1, -9, -3};
        int[] expected = {-9, -8, -5, -3, -2, -1};
        HeapSort.sort(array);
        assertArrayEquals(expected, array);
    }
}
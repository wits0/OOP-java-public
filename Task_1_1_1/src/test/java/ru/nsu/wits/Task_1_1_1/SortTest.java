package ru.nsu.wits.Task_1_1_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortTest {

    @Test
    void sort() {
        int[] array = new int[]{1, 3, 2};
        int[] result = HeapSort.sort(array);
        assertArrayEquals(new int[]{1, 2, 3}, result);
    }
}
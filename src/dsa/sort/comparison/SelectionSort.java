package dsa.sort.comparison;

import java.util.Arrays;

public class SelectionSort {
    /**
     * Selection Sort
     * 
     * TC: 
     *  - worst case: O(n^2)
     *      - array is sorted in reverse order
     *  - best case: O(n^2)
     *      - array is in sorted order
     * SC: O(1)
     * 
     * Selection sort is an unstable sorting algorithm
     * @param input
     */
    void sort(int[] input) {
        // Mutates arr so that it is sorted via selecting the minimum element and
        // swapping it with the corresponding index
        int min_index;
        for (int i = 0; i < input.length; i++) {
            min_index = i;
            for (int j = i + 1; j < input.length; j++) {
                if (input[j] < input[min_index]) {
                    min_index = j;
                }
            }
            // Swap current index with minimum element in rest of list
            swap(input, i, min_index);
        }
    }

    void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    public static void main(String[] args) {
        SelectionSort sort = new SelectionSort();
        int[] input = {2,1,3,4,-10,3,1};
        sort.sort(input);
        System.out.println(Arrays.toString(input));
    }
}

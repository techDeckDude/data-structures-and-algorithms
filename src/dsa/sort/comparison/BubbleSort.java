package dsa.sort.comparison;

import java.util.Arrays;

public class BubbleSort {

    /**
     * Bubble Sort
     * 
     * TC: 
     *  - worst case: O(n^2)
     *      - array sorted in reverse order
     *      - the worst possible input is a reversed list, where every element 
     *        has to be inserted at the very beginning of the list, which leads to 
     *        a total of 1 + 2 + ... + (n-1) or (n^2) swaps
     *  - best case: O(n)
     *      - array is already sorted
     * SC: O(1)
     * 
     * Bubble sort is a stable sorting algorithm
     * @param arr
     */
    public void sort(int[] arr) {
        // Mutates arr so that it is sorted via swapping adjacent elements until
        // the arr is sorted.
        boolean hasSwapped = true;
        while (hasSwapped) {
            hasSwapped = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    // Swap adjacent elements
                    swap(arr, i, i+1);
                    hasSwapped = true;
                }
            }
        }
    }

    void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    public static void main(String[] args) {
        BubbleSort sort = new BubbleSort();
        int[] input = {100,99,0,-99,-100};
        sort.sort(input);
        System.out.println(Arrays.toString(input));
    }
}

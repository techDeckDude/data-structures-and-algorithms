package dsa.sort.comparison;

import java.util.Arrays;

/**
 * When to use:
 *  - when input is partially sorted
 *  - the inpput is small
 * 
 * 
 * TC:
 *  - worst case: O(n^2) 
 *      - the worst possible input is a reversed list, where every element 
 *        has to be inserted at the very beginning of the list, which leads to 
 *        a total of 1 + 2 + ... + (n-1) or (n^2) swaps
 *  - best case: O(n)
 *      - the best possible input is an already sorted array
 * 
 * SC:
 *  - O(1)
 * 
 * Insertion sort is a stable sorting algorithm
 */
public class InsertionSort {
    void sort(int[] input) {
        // Mutates elements in input by inserting out of place elements into appropriate
        // index repeatedly until input is sorted
        for (int i = 1; i < input.length; i++) {
            int currentIndex = i;
            while (currentIndex > 0 && input[currentIndex - 1] > input[currentIndex]) {
                // Swap elements that are out of order
                swap(input, currentIndex, currentIndex - 1);
                currentIndex--;
            }
        }
    }

    void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    public static void main(String[] args) {
        InsertionSort sort = new InsertionSort();
        int[] input = new int[] {100,99,0,-99,-100};
        sort.sort(input);
        System.out.println(Arrays.toString(input));
    }
}

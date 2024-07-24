package dsa.sort.noncomparison;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * goal: sort integer by radix
 *  12,1,123
 *  radix = x=/1, x%=10 {0[],1[1],2[12],3[123]}
 *  radix = x/=10, x%=10 {0[1,12], 1[], 2[123], 3[]}
 *  radix = x/=100, x%=10 {0[1,12], 1[123]}
 * 
 * 
 * Here, nnn is the number of elements in the nums array, ddd is the maximum number of digits and bbb is the size of the bucket used.

Time complexity: O(d⋅(n+b))

We iterate on the array elements to find the maximum number and then find the count of its digits, thus taking O(n+d) time.
Then we sort the array for each integer place which will take O(n+b) time, thus for all ddd places it will take O(d⋅(n+b)) time.
At the end, we seperate out positive and negative numbers and reverse the negatives, which overall will take O(n) time.
Thus, overall it takes O((n+d)+d⋅(n+b)+n)=O(d⋅(n+b)) time.
 * 
 * SC: O(n+b)
 *  - we use additional arrays negatives and positives which use O(n)
 *    space and buckets which use O(n+b) space.
 */
public class RadixSort {
    // Bucket sort function for each place value digit.
    private void bucketSort(int[] arr, int placeValue) {
        ArrayList<List<Integer>> buckets = new ArrayList<>(10);
        for (int digit = 0; digit < 10; ++digit) {
            buckets.add(digit, new ArrayList<Integer>());
        }
        
        // Store the respective number based on its digit.
        for (int val : arr) {
            int digit = Math.abs(val) / placeValue;
            digit = digit % 10;
            buckets.get(digit).add(val);
        }

        // Overwrite 'arr' in sorted order of current place digits.
        int index = 0;
        for (int digit = 0; digit < 10; ++digit) {
            for (int val : buckets.get(digit)) {
                arr[index] = val;
                index++;
            }
        }
    }
    
    // Radix sort function.
    private void radixSort(int[] arr) {
        // Find the absolute maximum element to find max number of digits.
        int maxElement = arr[0];
        for (int val : arr) {
            maxElement = Math.max(Math.abs(val), maxElement);
        }
        int maxDigits = 0;
        while (maxElement > 0) {
            maxDigits += 1;
            maxElement /= 10;
        }

        // Radix sort, least significant digit place to most significant.
        int placeValue = 1;
        for (int digit = 0; digit < maxDigits; ++digit) {
            bucketSort(arr, placeValue);
            placeValue *= 10;
        }

        // Seperate out negatives and reverse them. 
        ArrayList<Integer> negatives = new ArrayList<>();
        ArrayList<Integer> positives = new ArrayList<>();
        for (int val : arr) {
            if (val < 0) {
                negatives.add(val);
            } else {
                positives.add(val);
            }
        }
        Collections.reverse(negatives);

        // Final 'answer' will be 'negative' elements, then 'positive' elements.
        int index = 0;
        for (int val : negatives) {
            arr[index] = val;
            index++;
        }
        for (int val : positives) {
            arr[index] = val;
            index++;
        }
    }

    public int[] sortArray(int[] nums) {
        radixSort(nums);
        return nums;
    }
}

package dsa.sort.noncomparison;

import java.util.HashMap;

/**
 * Counting sort is all about using a predefined range of “keys” 
 * 
 * 1. Counting sort can handle non-unique keys (input array can have duplicate elements) 
 * 2. Counting sort can handle non-consecutive keys (input array can have elements that don’t 
 *      exist within the predefined range of values) 
 * 3. Counting sort can handle non-numerical keys as long as they are constrained within an alphabet 
 *      of constrained size (e.g characters, objects with a predefined set of values)
 * 
 * 
 * A = [5,4,5,5,1,1,3]
 * 
 * As we did earlier, we can initialize a new array countscounts of size equal to the max(A)+1. 
 * Then, the core concept involves mapping each index i of the 
 * counts array to the number of occurrences of i in the original array 
 * 
 * C = [0, 2, 0, 1, 1, 3]
 * 
 * From this counts array (C), we can determine the starting index for each element 
 * in the original array From this counts array, we can determine the starting index for
 * each element in the original array. The starting indices can be found by calculating the 
 * cumulative sum of our previous counts array (index i is the sum of all preceding indices)
 * 
 * SI = [0, 0, 2, 2, 3, 4]
 * 
 * 
 * TC: O(n + k)
 * -    We iterate on the array elements while counting 
 *      the frequency and finding minimum and maximum values, 
 *      thus taking O(n) time.
 * -    Then we iterate on the input array's element's range which 
 *      will take O(k) time. Thus, overall it takes O(n+k) time.
 * SC:
 * -    We use a hash map counts which might store all O(n) 
 *      elements of the input array in worst-case.
 */
public class CountingSort {
    private void countingSort(int[] arr) {
        // Create the counting hash map.
        HashMap<Integer,Integer> counts = new HashMap<>();
        int minVal = arr[0], maxVal = arr[0];
        
        // Find the minimum and maximum values in the array,
        // and update it's count in the hash map.
        for (int i = 0; i < arr.length; i++) {
            minVal = Math.min(minVal, arr[i]);
            maxVal = Math.max(maxVal, arr[i]);
            counts.put(arr[i], counts.getOrDefault(arr[i], 0) + 1);
        }
        
        int index = 0;
        // Place each element in its correct position in the array.
        for (int val = minVal; val <= maxVal; ++val) {
            // Append all 'val's together if they exist.
            while (counts.getOrDefault(val, 0) > 0) {
                arr[index] = val;
                index += 1;
                counts.put(val, counts.get(val) - 1);
            }
        }
    }

    public int[] sortArray(int[] nums) {
        countingSort(nums);
        return nums;
    }
}

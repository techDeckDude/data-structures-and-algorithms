package dsa.sort.comparison;

/**
 * 
 TC:
 *  worst case: O(n^2)
 *          - O(n) elements visited in the partition function
 *          - O(n) times to split array to the base case of 1 element
 *      the worst possible input is a reversed list
 *
 *  best case: O(nlogn)
 *      - the best possible input is an already sorted array
 * 
 * Space Complexity: O(logn)
 *  - The recursive stack will take O(log⁡n) space
 * 
 */
public class QuickSort {
    void quicksort(int[] nums, int l, int r) {
        if(l > r) return;
        //partion array: items < pivot < items
        int partition = partition(nums, l, r);
        //sort left
        quicksort(nums, l, partition-1); 
        //sort right
        quicksort(nums, partition+1, r);

    }

    int partition(int[] nums, int l, int r) {
        /**
            - define pivot
            - iterate through array, swap elements to tail of items < pivot
         */
        int pivot = r;
        int i = l;
        for(int j = l; j < pivot; j++) {
            if(nums[j] < nums[pivot]) {
                swap(nums, i, j);
                //move up i to point to first item >= pivot seen so far
                i++;
            }
        }
        swap(nums, i, pivot);
        return i;
    }
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

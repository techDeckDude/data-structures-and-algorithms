package dsa.sort.comparison;
import java.util.Arrays;

/**
 * 
 TC:
 *  worst case: O(nlogn)
 *          - O(n) elements copied in the merge function (summed across all levels)
 *          - O(logn) times to split array to the base case of 1 element
 *      - no possible worst input. run time will won't change but number of
 *        comparisons could be more depending on how the data is sorted 
 *
 *  best case: O(nlogn)
 *      - no possible best input. run time will won't change but number of
 *      comparisons could be more depending on how the data is sorted 
 * 
 * Space Complexity: O(n)
 *  - The recursive stack will take O(log⁡n) space and we used an additional array temporaryArray of size n.
 *    Thus, overall we use O(log⁡n+n) = O(n) space.
 */
public class MergeSort {
    public void sort(int[] input) {
        int[] aux = new int[input.length];
        int start = 0;
        int end = input.length-1;

        sort(input, start, end, aux);

    }

    public void sort(int[] input, int start, int end, int[] aux) {
        //base case out if merge range is of size 0 or 1
        if(start >= end) {
            return;
        }

        int mid = start + (end-start)/2;
        sort(input, start, mid, aux);
        sort(input, mid+1, end, aux);
        
        merge(input, start, mid, end, aux);
    }

    /**
     * two steps to the merge function:
     * 1) copy the merge range from input array into aux array:
     *   - this is necessary because you need extra space
     *     to store the values that will be overwritten in the input array
     * 2) overwrite values in input array:
     *  - need to walk through aux array (which is same length as input array)
     *  - use two more pointers, i and j, to walk through merge range in aux
     *  - k is a pointer for input array (start to end)
     *  - i is a pointer for left half of array (start to mid)
     *  - j is a pointer for right hald of array (mid+1 to end)
     * 
     *  - when i is > mid, you've exhausted all i's
     *    - i will always be between start and mid, which is inbounds of k (which is start to end)
     * - when j is > end, you've exhausted all j's
     *   - j will always be between start and mid, which is inbounds of k (which is start to end)
     * 
     * 
     * @param input
     * @param start
     * @param mid
     * @param end
     * @param aux
     */
    public void merge(int[] input, int start, int mid, int end, int[] aux) {
        //copy merge range into aux
        for(int k = start; k <= end; k++) {
            aux[k] = input[k];
        }

        int i = start;//increments to mid
        int j = mid+1;//increments to end
        for(int k = start; k <= end; k++) {
            if(i > mid) {
                input[k] = aux[j];
                j++;
            } else if(j > end) {
                input[k] = aux[i];
                i++;
            }else if(aux[i] < aux[j]) {
                input[k] = aux[i];
                i++;
            } else {
                input[k] = aux[j];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[] input = new int[] {20,10,300,60,25,15};
        MergeSort ms = new MergeSort();
        ms.sort(input);
        System.out.println(Arrays.toString(input));
        
    }
}

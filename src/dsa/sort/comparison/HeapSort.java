package dsa.sort.comparison;

import java.util.Arrays;


/**
 * 
 * Key Facts:
 * 
 * Run Time Complexity:
 * 
 * TC:
 *  worst case: O(n + nlogn) ~ O(nlogn)
 *          - O(n) to heapify (turn unsorted array into a heap)
 *          - O(logn) preserve heap property after removal
 *          - O(n) to remove all elements
 *      the worst possible input is a reversed list
 *
 *  best case: O(nlogn)
 *      - the best possible input is an already sorted array
 * 
 * SC: O(logn)
 *  - The recursive stack will take O(log⁡n) space
 * 
 * Heap Sort is an unstable sorting algorithm
 * 
 * 1) How is Heap Sort similar to Selection Sort? 
 *      1) Both sorts typically operate on arrays or some other indexable-constant-access data structure
 *      2) They both push the largest (or smallest) element to the front of the array (data structure) in O(n) time.
 *          - Heap Sort visits all indicies and does O(1) number of comparisons (for an index i, comapres i to i+1 and i+2)
 *          - Selection Sort visits alls indicies and does O(1) number of comparisons (for an index i, compares i to i-1)
 * 
 * 2) How is Heap Sort different from Selection Sort?
 *      1)  Selection sort repeatedly finds the minimum element by traversing the unsorted part of the list. 
 *          Heapsort improves upon this by quickly finding the maximum element through the maintenance of a max-heap.
 *      2)  Selection Sort pushes the smallest element to the front of the array.
 *          Heap Sort (typically) pushes the max element to the front of the array (heapification)
 *      3)  Selection Sort partially sorts the array by 1 element at a time
 *          Heap Sort partially sorts the array by 1 element per sub tree, resulting in more elements being closer to
 *          their final sorted poistion after heapification (which takes O(n) time) when compared to Selection Sort. I.e. Heap Sort does
 *          more partial sorting than Selection Sort.
 * 
 * 3) How does Heap Sort work?
 *      There are three parts:
 *          1) heapify the input array which takes O(n) time. This creates the initial heap, putting the max (or min) element at index 0
 *          2) Removing the max (or min) element from the heap. This happens O(n) times
 *          3) Restoring heap property after removal takes O(log n) time. This happens n times, becasuse there are n removals.
 * 
 * 4) Facts about Heap Sort
 *      1) Given an input array with length n, the first (n/2 - 1) values have "children" while the second (n/2 - 1) values dont
 *      2) The heapification process starts one level up from the leaf nodes, transforming the base of these substrees trees into heap,
 *          and ensuring that the heap property is satisfied before moving up to the next level of the tree
 *      3) The heapification process traverses the tree from the leaf nodes to the root nodes in level order (bfs)
 * 
 * 
 * The main advantage of heapsort is it's generally much faster than the other comparison based sorts on sufficiently large inputs 
 * as a consequence of the running time. However, there are a few undesirable qualities in the algorithm. 
 * For one, it is not a stable sort. It also turns out that in practice, this algorithm performs worse than other 
 * O(nlogn) sorts as a result of bad cache locality properties. Heapsort swaps elements based on locations in heaps, 
 * which can cause many read operations to access indices in a seemingly random order, causing many cache misses, 
 * which will result in practical performance hits.
 * 
 * 
 * 
 */
public class HeapSort {
    public int[] heapsort(int[] arr) {
    
        // Find the Length of the array
        int n=arr.length;
        
        // Build heap by rearranging the array
        for(int i = n/2; i >= 0; i--) {
            heapify(arr, i, n);
        }

        //extract all elements
       for(int i = arr.length-1; i >= 0; i--) {
        extract(arr, i);
       }
        
        return arr;
      }

    void extract(int[] nums, int size) {
        int temp = nums[size];
        nums[size] = nums[0];
        nums[0] = temp;
        heapify(nums, 0, size);
    }

    void heapify(int[] nums, int root, int n) {
        int lc = 2*root + 1;
        int rc = 2*root + 2;
        int max = root;

        if(lc < n && nums[lc] > nums[max]) {
            max = lc ;
        }
        if(rc < n && nums[rc] > nums[max]) {
            max = rc;
        }

        if(max != root) {
            int temp = nums[max];
            nums[max] = nums[root];
            nums[root] = temp;

            heapify(nums, max, n);
        }
    }

    public static void main(String[] args) {
        HeapSort hs = new HeapSort();
        int[] input = {10,6,7,5,15,17,12,1,200,3,35,45,2,-1,-40};
        // hs.buildHeap(input);
        hs.heapsort(input);
        System.out.println(Arrays.toString(input));
    }
}
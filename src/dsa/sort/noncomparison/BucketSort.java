package dsa.sort.noncomparison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BucketSort {
    public void bucketSort(int[] arr, int K) {
        List<List<Integer>> buckets = new ArrayList<List<Integer>>(K);
        int shift = Arrays.stream(arr).min().getAsInt();
        int maxValue = Arrays.stream(arr).max().getAsInt() - shift;
        // place elements into buckets
        int bucketSize = maxValue / K;
        if (bucketSize < 1) {
            bucketSize = 1;
        }
        for (int elem : arr) {
            // same as K * arr[i] / max(lst)
            int index = (int) (elem - shift) / bucketSize;
            if (index == K) {
                // put the max value in the last bucket
                buckets.get(K - 1).add(elem);
            } else {
                buckets.get(index).add(elem);
            }
        }

        // sort individual buckets
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        // convert sorted buckets into final output
        List<Integer> sortedList = new ArrayList<Integer>();
        for (List<Integer> bucket : buckets) {
            sortedList.addAll(bucket);
        }

        // perfectly fine to just return sortedList here
        // but common practice is to mutate original array with sorted elements
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sortedList.get(i);
        }
    }

    public static void main(String[] args) {
        BucketSort bucketSort = new BucketSort();
        
    }
}

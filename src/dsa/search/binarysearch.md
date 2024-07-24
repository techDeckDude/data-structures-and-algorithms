Binary Search

https://en.wikipedia.org/wiki/Binary_search_algorithm 

Java JDK Implementation (https://github.com/openjdk/jdk/blob/jdk-14-ga/src/java.base/share/classes/java/util/Collections.java#L215)
General Info About Binary Search
What is it used for?
Finding the position of a target element in a sorted array
Finding next-smallest or next-largest element in a sorted array

Important Info About Binary Search
Hash Tables are faster than binary search for finding an exact value
However, binary search is faster than a hash table for things like:
Finding next-smallest or next-largest element in the array


There are numerous variations of binary search. In particular, fractional cascading speeds up binary searches for the same value in multiple arrays. Fractional cascading efficiently solves a number of search problems in computational geometry and in numerous other fields. Exponential search extends binary search to unbounded lists. The binary search tree and B-tree data structures are based on binary search.

Binary Search Implementations
Using a specific binary search implementations may make some binary search problems easier to solve than if a different implementation was used
From my studying so far, I’ve seen that at least the first binary search template can be used to solve all problems presented in the explore card













Template 1:

Base cases:
When search space (distance between l and r) == 0
notes
This templates computes all possible mid points within the loop, and terminates when l > r
The size of the search space will always be >= 1
A search space of size 3 gets reduced to a search space of size 1
This template appears to search all possible ‘targets’ within the loop
It does this by including the entire search space between l and r within the loop, which is the opposite of the other templates which only include the search space between l and r if the search space is > 1 (template 2) or search space is > 2 (template 3)
 while(l <= r)

This loop terminates with r pointing to the nearest integer to the target value because l will be greater than r when the loop terminates
Use this when the target element is the floor of a value, r will point to the index that is the nearest floor integer of where the target value would be located
This will also allow you to check all values between l and r, if you did while(l < r) this would leave only check (r-l)+1 possible values instead of r-l possible values


Template 2:

Base cases (loop invariant) (when loop terminates):
When search space (distance between l and r) == 1
notes
The size of the search space will always be >= 2
A search space of size 3 will reduce to a search space of size 2
The loop will terminate when the search space is < 2
3 elements reduces to 1 element if target is > mid (looking right)
3 elements reduces to 2 elements if target < mid (looking left)
This implementation (template) of binary search will always end with l pointing to the last item found in the search when the loop terminates
When does this template find the target value:
If remaining search space size == 3 and target is at index 3, it will not be found at mid, and will be located at the l pointer when the loop terminates
If remaining search space size == 2 and target it at index 1 it will be found at mid
















Template 3:
 

Base cases (loop invariant) (when loop terminates):
When search space (distance between l and r) == 2
notes:
Search space is always at least of size 3
The loop terminates when the size of search space < 3
If you have a search space of size 3, it will reduce to a search space of size 2, using this algorithm
When the search space reduces to size 2, the l and r pointers point to the two remaining values, respectively

Follow Up On
Ternary search
Binary search math proof (?)
Time complexity
https://www.youtube.com/watch?v=dWgyqtGnlNs  



Finding The Target Element (No duplicates)
Finding The Target Element (Duplicates)
Finding The Leftmost Target Element
Finding The Rightmost Target Element

Finding Approximate Matches
Finding a target element’s rank in the array
The number of elements smaller 
Rank queries => uses the procedure for finding the leftmost target element. The number of elements less than the target is return by the procedure
Predecessor => next-smallest element
Performed with rank queries using the procedure to find the leftmost target element. If the rank of the target value is r, its predecessor is r-1
Successor => next-largest element
Performed with rank queries using the procedure to find the rightmost target element. If the rank of the target value is r, its successor is r+1
Nearest neighbor
The nearest neighbor of the target element is either the predecessor or successor, whichever is closer
range queries => seeking the number of elements between two values can be performed with two rank queries
Once the ranks of the two values are known, the number of elements greater than or equal to the first value and less than the second is the difference of the two ranks. This count can be adjusted up or down by one according to whether the endpoints of the range should be considered to be part of the range and whether the array contains entries matching those endpoints.[13]




Fundamentals
Base cases:
Always start w/ the base case
Target <= arr[mid], L = index of target after loop
Target >= arr[mid], R = index of target after loop

When target <= arr[mid]:

Arr = [1], target = 1
L => index 0 after loop

Arr = [1,2], target = 1
L => index 0 after loop

Arr = [1,2],  target = 2
L => index 1 after loop

Arr = [1,2,3], target = 1
L => index 0 after loop

Arr = [1,2,3], target = 2
L => index 1 after loop

Arr = [1,2,3], target = 3
L => index 2 after loop

Arr = [1,1], target = 1
L => index 0 after loop

Arr = [1,1], target = 2
L => index 2 after loop

Leetcode Problems
Binary Search - LeetCode 
Sqrt(x) - LeetCode
Guess Number Higher or Lower - LeetCode
Search in Rotated Sorted Array - LeetCode
First Bad Version - LeetCode
Find Peak Element - LeetCode
Find Minimum in Rotated Sorted Array - LeetCode
Find First and Last Position of Element in Sorted Array - LeetCode
Find K Closest Elements - LeetCode
LeetCode - The World's Leading Online Programming Learning Platform
Search in a Sorted Array of Unknown Size - LeetCode
Valid Perfect Square - LeetCode
Find Smallest Letter Greater Than Target - LeetCode
Find Minimum in Rotated Sorted Array II - LeetCode
Intersection of Two Arrays - LeetCode
Intersection of Two Arrays II - LeetCode
Two Sum II - Input Array Is Sorted - LeetCode
Find the Duplicate Number - LeetCode
Median of Two Sorted Arrays - LeetCode
Split Array Largest Sum - LeetCode
K-th Smallest Distance Pair







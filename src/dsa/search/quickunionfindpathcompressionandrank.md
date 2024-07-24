- [Union By Rank Video](https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3879/)
- [Path Compression Video](https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3880/)

**TC:**
- constructor:    O(n)
- find:           O(1)
- union:          O(1)
- connected:      O(1)

**SC:**
- O(n) to store root and rank arrays

**Description**
A disjoint-set data structure also called a union–find data structure or merge–find set, is a data structure that stores a collection of disjoint (non-overlapping) sets. Equivalently, it stores a partition of a set into disjoint subsets. It provides operations for adding new sets, merging sets (replacing them by their union), and finding a representative member of a set. It implements two useful operations:

- **Find:** Determine which subset a particular element is in. This can be used to determine if two elements are in the same subset.
- **Union:** Join two subsets into a single subset.   
   
**benefits of path compression with union rank:**
-   find function can compress the height of tall graphs (trees)
    -   will initially take O(n) to find the root of given node, before compression
    -   subsequent calls will take at most O(1) to find root of given node
-   union function can merge two trees in a balanced way
-   we save time in the find function by
    updating the parent node of all traveresed
    nodes to their root node
-   when we search for the root node again
    we only need to traverse two elements
    to find its root node, which is highly efficient
-   the union function balances the tree,
    preventing it from becoming a linked list
    and essentially keeping the height of the 
    tree h = ~~log(n) = O(h) = O(logn) instead of O(h) = O(n)~~
-   find function will run in ~~log(n)~~ time now
    because it will never produce a linked list
    since the tree is balanced after every union
 
issues with path compression with union rank:
# Caching
- In any memory-capable digital systems, from small cell phones to a huge supercomputers and datacenters, the performance of data buffer management is mainly determined by a replacement algorithm that decides which data block will be evicted when a new data block needs to be allocated as the buffer space is full. The replacement concept is simple but its algorithmic optimization and system implementations are challenging and difficult.
- The most commonly used algorithm is the LRU algorithm that makes a simple assumption for all the data accesses: if a data block is accessed once, it will be accessed again. This locality metric is also called ``recency'', which is implemented by a LRU stack. Each data access is recorded in the LRU stack, where the top entry is the most recent accessed (MRU) and the bottom entry is the least recent accessed (LRU). The LRU entry is the evicted item as the buffer is full (reflected by the full LRU stack)
## Caching vs Hash Maps
https://github.com/ben-manes/caffeine/wiki

**Differences between maps and caches**
- a map persist all elements that arre added to it until they are explicitly removed.
- a cache is configured to evict entries automatically, in order to constrain its memory footprint
- In some cases a LoadingCache or AsyncLoadingCache can be useful even if it doesn't evict entries, due to its automatic cache loading.


## Types of Caches
**Tiny-LFU/W-Tiny LFU** 
- [Tiny-LFU research paper](http://arxiv.org/pdf/1512.00727.pdf%20[3]%20http://highscalability.com/blog/2016/1/25/design-of-a-modern-cache.html)
- [Solr CaffeineCache api docs](https://solr.apache.org/docs/8_3_0/solr-core/org/apache/solr/search/CaffeineCache.html) 
- [Solr CaffeineCache src code](https://github.com/apache/solr/blob/main/solr/core/src/java/org/apache/solr/search/CaffeineCache.java)
- [Solr CaffeineCacheTest src code](https://github.com/apache/solr/blob/main/solr/core/src/test/org/apache/solr/search/TestCaffeineCache.java)

**LIRS Cache**
- [LIRS Cache reasearch paper - part 1](https://web.cse.ohio-state.edu/~zhang.574/lirs-sigmetrics-02.html)
- [LIRS Cache reasearch paper - part 2](https://web.cse.ohio-state.edu/~zhang.574/hpcs/WWW/HTML/publications/papers/TR-02-6.pdf)
- [Apache Oak LIRS Cache src code](https://github.com/apache/jackrabbit-oak/blob/trunk/oak-core-spi/src/main/java/org/apache/jackrabbit/oak/cache/CacheLIRS.java)

LIRS Caching Solution:
- LIRS represents Low Inter-reference Recency Set that is the data set to be kept in the buffer cache. Inter-reference recency is equivalent to reuse-distance for a accessing a data block, which measures the number of distinct data entry accesses between two consecutive accesses of the data block. The data replacement decision of the LIRS algorithm is made based on the reuse distance, namely to evict the high reuse distance data blocks and keep the low reuse distance data blocks. In this way, the three LRU issues are addressed. The LIRS caching algorithm is implemented by a data structure of two stacks, retaining the same complexity as that of LRU.
- After many years` efforts from software industries since the LIRS paper was published in 2002, the dominant LRU-like algorithms have been gradually replaced by LIRS-like algorithms in major operating systems, database systems, and data centers, including BSD, Linux, MySQL, Infinispan, and other production systems.

IRR
- Inter-Reference Recency is the recorded history information of each block
- The irr of a block referes to the number of other blocks accessed from last reference to the current time
- the recency refers to the number of other blocks accessed from last reference to the current time





**LRU**

Drawbacks of LRU
- However, the LRU algorithm fails to handle the following three data access patterns: (1) each data block is only accessed once in a format of sequential scans. In this situation, the buffer would be massively polluted by weak or no locality data blocks. (2) For a cyclic (loop-like) data access pattern, where the loop length is slightly larger than the buffer size, LRU always mistakenly evicts the blocks that will be accessed soon in the next loop. (3) In multiple streams of data accesses where each stream has its own probability for data re-accesses, LRU could not distinguish the probabilities among the streams.
- Although the LRU algorithm has these three critical flaws, it has been widely used in production systems due to its merits of low complexity and low overhead implementation. Since the LRU replacement algorithm was proposed and its approximation was implemented in 1960`s, computer scientists and system engineers have made continuous and tireless efforts to improve replacement algorithms analytically and systematically for a common goal of addressing the flaws of LRU, subject to keeping the merits of LRU (working well for strong locality data accesses and low overhead implementation).

**LFU**


## Cache Implementation
1. Cache: 
    - uses a hash table for constant time access
    - the eviction policy is used to manage the size of the hashtable
2. Hash table
    - uses a resizable, indexable list of nodes (which are lists themselves)
    - the size of the list is determined by...
    - a load factor is determined by...
    - each index in the list will store a list of nodes where each list of nodes at index i is a list of collisions for that index
    - collisions can be stored as a list (O(n) search time) or as space effient balanced BST
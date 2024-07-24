package dsa.store.hash;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 *  An LRU cache is a cache (https://en.wikipedia.org/wiki/Cache_(computing)) 
 *  that uses the LRU replacement policy (https://en.wikipedia.org/wiki/Cache_replacement_policies#Least_recently_used_(LRU)).
    The cache has the following api:
    get(int key): Returns the value corresponding to the key key. If the key does not exist null is returned instead.
    put(int key, int value): Inserts the key-value pair into the cache. If an item with key key already exists then the value will just be updated.

    These are the special properties of the cache:
    When an item is retrieved by key it moves to the conceptual "front" of the cache (it is the most recently used item)
    When an item is updated it moves to the conceptual "front" of the cache (it is the most recently used item)
    When a new item is inserted it is inserted to the "front"
    When the cache goes over-capacity it will remove the least recently used item which will sit at the conceptual "end" of the cache to make room for the new item inserted
 */
class LRUCache {
    Map<Integer, ListNode> hashtable = new HashMap<Integer, ListNode>();
    ListNode head;
    ListNode tail;

    int totalItemsInCache;
    int maxCapacity;

    public LRUCache(int maxCapacity) {
      // Cache starts empty and capacity is set by client
      totalItemsInCache = 0;
      this.maxCapacity = maxCapacity;

      // Dummy head and tail nodes to avoid empty states
      head = new ListNode();
      tail = new ListNode();

      // Wire the head and tail together
      head.next = tail;
      tail.prev = head;
    }

    public Integer get(int key) {
      ListNode node = hashtable.get(key);

      if (node == null) {
        return null;
      }

      // Item has been accessed. Move to the front of the cache
      moveToHead(node);

      return node.value;
    }

    public void put(int key, int value) {
      ListNode node = hashtable.get(key);

      if (node == null) {
        // Item not found, create a new entry
        ListNode newNode = new ListNode();
        newNode.key = key;
        newNode.value = value;

        // Add to the hashtable and the actual list that represents the cache
        hashtable.put(key, newNode);
        addToFront(newNode);
        totalItemsInCache++;

        // If over capacity remove the LRU item
        if (totalItemsInCache > maxCapacity) {
          removeLRUEntry();
        }
      } else {
        // If item is found in the cache, just update it and move it to the head of the list
        node.value = value;
        moveToHead(node);
      }
    }

    private void removeLRUEntry() {
      ListNode tail = popTail();

      hashtable.remove(tail.key);
      --totalItemsInCache;
    }

    private ListNode popTail() {
      ListNode tailItem = tail.prev;
      removeFromList(tailItem);

      return tailItem;
    }

    private void addToFront(ListNode node) {
      // Wire up the new node being to be inserted
      node.prev = head;
      node.next = head.next;

      /*
        Re-wire the node after the head. Our node is still sitting "in the middle of nowhere".
        We got the new node pointing to the right things, but we need to fix up the original
        head & head's next.

        head <-> head.next <-> head.next.next <-> head.next.next.next <-> ...
        ^            ^
        |- new node -|

        That's where we are before these next 2 lines.
      */
      head.next.prev = node;
      head.next = node;
    }

    private void removeFromList(ListNode node) {
      ListNode savedPrev = node.prev;
      ListNode savedNext = node.next;

      savedPrev.next = savedNext;
      savedNext.prev = savedPrev;
    }

    private void moveToHead(ListNode node) {
      removeFromList(node);
      addToFront(node);
    }

    private class ListNode {
      int key;
      int value;

      ListNode prev;
      ListNode next;
    }
  }

package dsa.store.graph;

public class ShortestPath {
    /**
     *  Dijkstra’s algorithm solves the “single-source shortest path” 
     *  problem in a weighted directed graph with non-negative weights.
     * 
     *  Key Elements for Solving "Single-source shortest path"
     *      Edge Relaxation:
     *          - the process of finding the minimum time between two connected nodes
     *          - imagine that each path is a rubber band of length 1. 
     *              the original path from A to D is of length 3, so the rubber band was 
     *              stretched to 3 times its original length. When we relax the path to length 2, 
     *              by visiting C first, the rubber band is now only stretched to twice its length, 
     *              so you can imagine the rubber band being relaxed, hence the term edge relaxation.
     * 
     * 
     * 
     * TC: O(V + ElogV)
     * SC: O(V + E), store V verticies in our minHeap
     */
    class Dijkstra {
        /*
         * requirements:
         *  -   table for storing the shortest path to a node
         *          [
         *            [0,inf],
         *            [1,inf],
         *            [2,inf]
         *            [3, inf]  
         *          ]
         * class Pair <N,W> {
         *  N node;
         *  W weight;
         *  public(N node, W weight) {
         *      this.node = node;
         *      this.weight = weight;
         *  }
         * }
         * 
         * 
         * bfs the graph to find the shortest path:
         *  Pair<Integer, Integer> srcPair = new Pair<>(src, 0);
         *  queue.add(srcPair)
         * while(!queue.isEmpty()) {
         *  int currPair = queue.poll()
         *  int currNode = currPair.node;
         *  int currWeight = currPair.weight;
         *  //get shortest path from table
         *  int shortestPathToSrc = table[currNode];
         *  //update table to contain shortest path seen so far to this node
         *  shortestPathToSrc = Math.min(shortestPathToSrc, currWeight)
         *  if(!visited.contains(currNode)) {
         *      visited.add(currNode);
         *      
         *  }
         *  
         *  
         * 
         * }
         * 
         * return table[dst];
         * 
         */
    }
}

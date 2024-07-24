package dsa.store.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 
 * A spanning tree is a connected subgraph in an undirected graph 
 * where all vertices are connected with the minimum number of edges. 
 * In Figure 9, all pink edges [(A, B), (A, C), (A, D), (A, E)] form a tree, 
 * which is a spanning tree of this undirected graph. 
 * Note that [(A, E), (A, B), (B, C), (C, D)] is also a spanning tree of the undirected graph. 
 * Thus, an “undirected graph” can have multiple spanning trees.
 * 
 * 
 * 
 * A minimum spanning tree is a spanning tree with the 
 * minimum possible total edge weight in a “weighted undirected graph”. 
 * In Figure 10, a spanning tree formed by green edges [(A, E), (A, B), (B, C), (C, D)] 
 * is one of the minimum spanning trees in this weighted undirected graph. 
 * Actually, [(A, E), (E, D), (A, B), (B, C)] forms another minimum spanning tree 
 * of the weighted undirected graph. 
 * Thus, a “weighted undirected graph” can have multiple minimum spanning trees.
 * 
 * 
 * In this chapter, we will learn about the "cut" property and 
 * two algorithms for constructing a “minimum spanning tree”:
 *      - Kruskal’s Algorithm
 *      - Prim’s algorithm
 * 
 * 
 * To understand the “cut property”, we need to understand two basic concepts.
 *      - First, in Graph theory, a “cut” is a partition of vertices in a “graph” into two disjoint subsets. 
 *        Figure 11 illustrates a “cut”, where (B, A, E) forms one subset, and (C, D) forms the other subset.
 *      - Second, a crossing edge is an edge that connects a vertex in one set with a vertex in the other set. 
 *        In Figure 11, (B, C), (A, C), (A, D), (E, D) are all “crossing edges”.
 * 
 * 
 * “cut property” refers to:
 *      For any cut C of the graph, if the weight of an edge E in the cut-set of C 
 *      is strictly smaller than the weights of all other edges of the cut-set of C, 
 *      then this edge belongs to all MSTs of the graph.
 * 
 */
public class MinimumSpanningTree {
    /**
     * 
     * TC:
     *  - worst case: O(n^2 * log(n))
     * 
     * SC: O(n^2)
     *  - to store all of the edges
     * 
     */
    class KruskalsAlgorithm {
        /**
         * Using Kruskals Algorithm to return the minimum cost to make all points
         * connected.
         */
        public int minCostConnectPoints(int[][] points) {
            // calculate all weights
            ArrayList<Edge> edges = new ArrayList<>();
            for (int i = 0; i < points.length; i++) {
                // compare point[i][x,y] to point[i+1][x,y]
                int[] point1 = points[i];
                for (int j = i + 1; j < points.length; j++) {
                    int[] point2 = points[j];
                    // get manhattan distance
                    int weight = Math.abs(point1[0] - point2[0]);
                    weight += Math.abs(point1[1] - point2[1]);
                    // "compare", create edge
                    Edge edge = new Edge(i, j, weight);
                    edges.add(edge);
                }
            }

            QuickFind qf = new QuickFind(points.length);

            int n = 0;
            int minCost = 0;

            // will iterate in sorted order
            Collections.sort(edges, (a, b) -> Integer.compare(a.weight, b.weight));

            for (Edge edge : edges) {
                int node1 = edge.node1;
                int node2 = edge.node2;
                if (!qf.isConnected(node1, node2)) {
                    qf.union(node1, node2);
                    n++;
                    minCost += edge.weight;
                }
                // The MST becomes complete as soon as it contains (n−1) edges because a
                // tree with n nodes will always have (n−1) edges.
                if (n == points.length - 1) {
                    break;
                }
            }

            return minCost;
        }

        class Edge {
            int node1;
            int node2;
            int weight;

            public Edge(int node1, int node2, int weight) {
                this.node1 = node1;
                this.node2 = node2;
                this.weight = weight;
            }
        }

        class QuickFind {
            int[] root;
            int[] rank;

            public QuickFind(int numNodes) {
                root = new int[numNodes];
                rank = new int[numNodes];

                for (int i = 0; i < numNodes; i++) {
                    root[i] = i;
                    rank[i] = 1;
                }
            }

            int find(int x) {
                if (x != root[x]) {
                    return root[x] = find(root[x]);
                }

                return root[x];
            }

            boolean isConnected(int x, int y) {
                return find(x) == find(y);
            }

            void union(int x, int y) {
                int rootx = find(x);
                int rooty = find(y);

                if (rootx != rooty) {
                    if (rank[rootx] > rank[rooty]) {
                        root[rooty] = rootx;
                        rank[rootx]++;
                    } else {
                        root[rootx] = rooty;
                        rank[rooty]++;
                    }
                }
            }
        }
    }

    /**
     * 
     * TC:
     *  - worst case: O(n^2 * log(n))
     * 
     * SC: O(n^2)
     *  - to store all of the edges
     * 
     */
    class PrimsAlgorithm {
        public int minCostConnectPoints(int[][] points) {
            int n = points.length;

            // Min-heap to store minimum weight edge at top.
            PriorityQueue<Pair<Integer, Integer>> heap = new PriorityQueue<>((a, b) -> (a.getKey() - b.getKey()));
            ;

            // Track nodes which are included in MST.
            boolean[] inMST = new boolean[n];
            heap.add(new Pair<Integer, Integer>(0, 0));
            int mstCost = 0;
            int edgesUsed = 0;

            while (edgesUsed < n) {
                Pair<Integer, Integer> topElement = heap.poll();

                int weight = topElement.getKey();
                int currNode = topElement.getValue();

                // If node was already included in MST we will discard this edge.
                if (inMST[currNode]) {
                    continue;
                }

                inMST[currNode] = true;
                mstCost += weight;
                edgesUsed++;

                for (int nextNode = 0; nextNode < n; ++nextNode) {
                    // If next node is not in MST, then edge from curr node
                    // to next node can be pushed in the priority queue.
                    if (!inMST[nextNode]) {
                        int nextWeight = Math.abs(points[currNode][0] - points[nextNode][0]) +
                                Math.abs(points[currNode][1] - points[nextNode][1]);

                        heap.add(new Pair<Integer, Integer>(nextWeight, nextNode));
                    }
                }
            }

            return mstCost;
        }

        public class Pair<K, V> {
            K key;
            V value;

            public Pair(K key, V value) {
                this.key = key;
                this.value = value;
            }

            public K getKey() {
                return key;
            }

            public V getValue() {
                return value;
            }

        }
    }
}

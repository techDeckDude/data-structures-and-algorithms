package dsa.store.graph;

/**
 * 
 * The primary use of disjoint sets is to address the connectivity between 
 * the components of a network. The “network“ here can be a computer network 
 * or a social network. For instance, we can use a disjoint set to determine
 *  if two people share a common ancestor.
 * 
 * 
 * Terminology:
 *      Parent node: the direct parent node of a vertex. 
 *      Root node: a node without a parent node
 * 
 * The two important functions of a disjoint set:
 *      find function: finds the root node of a given vertex
 *      union function: unions two vertices and makes their root nodes the same
 * 
 * 
 * 
 * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 * Summary of the "disjoint set" data structure:
 * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 * 
 *      The main idea of a “disjoint set” is to have all connected vertices 
 *      have the same parent node or root node, whether directly or indirectly connected. 
 *      To check if two vertices are connected, we only need to check if they have the same root node.
 * 
 *      The two most important functions for the “disjoint set” data structure are 
 *      the find function and the union function. The find function locates the root node of a given vertex. 
 *      The union function connects two previously unconnected vertices by giving them the same root node. 
 *      There is another important function named connected, which checks the “connectivity” of two vertices. 
 *      The find and union functions are essential for any question that uses the “disjoint set” data structure.
 * 
 * NOTE:    you have to be given the exact nodes that need to be connected as input. If you need to "find" the
 *          nodes that need to be connected, this is not the algorithm to use.
 *
 *           Example of connecting components but you're given the nodes:
 *           https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/description/
 * 
 *           Example of connecting components but you're NOT given the nodes, 
 *           and you need find the nodes:
 *           https://leetcode.com/problems/number-of-islands/
 * 
 * 
 * 
 * %%%%%%%%%%%%%%%%%%%
 * Note from Leetcode:
 * %%%%%%%%%%%%%%%%%%%
 * 
 *      The code for the disjoint set is highly modularized. 
 *      You might want to become familiar with the implementation. 
 *      I would highly recommend that you understand and memorize 
 *      the implementation of “disjoint set with path compression and union by rank”.
 * 
 *      Finally, we strongly encourage you to solve the exercise problems using the 
 *      abovementioned implementation of the “disjoint set” data structure. 
 *      Some of these problems can be solved using other data structures and algorithms, 
 *      but we highly recommend that you practice solving them using the “disjoint set” data structure.
 * 
 * 
 */

public class DisjointSet {
    /**
     * Quick Find implementation of a Disjoint Set and cover 
     * 
     * TC:
     *      constructor: O(n)
     *      find:        O(1)
     *      union:       O(n)
     *      connected:   O(1)
     * 
     * SC:
     *      O(n) space to store the root array of size N
     * 
     * 
     * - average case TC to union n elements is always n^2 
     *   because of the loop from 0 to n in the union function
     *   which is called for n vertexes in the graph
     * 
     * 
     * 
     * benefits of quick find:
     *      -   the union function attempts to balance the
     *          tree by making x the root of all values that 
     *          has y as the root
     *      -   the base case of a unioning 3 vertex forrest will always
     *          produce a tree of minimum height when unioning x and y
     *          no matter if x is height of 2 and y is height of 1
     *          or vice versa
     *      -   unlike quick union which produces the posibility of creating 
     *          a tree with the max height possible (linked list)
     *          depending on which order you tree to union x and y in
     * 
     * 
     * issues with quick find:
     *      -   the union function always runs in O(n) time
     * 
     */
    class QuickFind {
        private int[] root;
        
        public QuickFind(int size) {
            root = new int[size];
            for(int i = 0; i < size; i++) {
                root[i] = i;
            }
        }

        public int find(int x) {
            return root[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX != rootY) {
                for(int i = 0; i < root.length; i++) {
                    if(root[i] == rootY) {
                        root[i] = rootX;
                    }
                }
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }

        public void main(String[] args) throws Exception {
            QuickFind uf = new QuickFind(10);
            // 1-2-5-6-7 3-8-9 4
            uf.union(1, 2);
            uf.union(2, 5);
            uf.union(5, 6);
            uf.union(6, 7);
            uf.union(3, 8);
            uf.union(8, 9);
            System.out.println(uf.connected(1, 5)); // true
            System.out.println(uf.connected(5, 7)); // true
            System.out.println(uf.connected(4, 9)); // false
            // 1-2-5-6-7 3-8-9-4
            uf.union(9, 4);
            System.out.println(uf.connected(4, 9)); // true
        }
    }

    /**
     * 
     * quick union implementation of the Disjoint Set.
     * 
     * TC:
     *      constructor:    O(n)
     *      find:           O(n)
     *      union:          O(n)
     *      compare:        O(1)
     * 
     * SC:
     *      O(n) to store root array of size n
     * 
     * 
     * worst case: find is O(n) if the graph is a linked list.
     *             find function in Quick Union is capable of producing a linked list
     *             while quick find is not. n vertexes in the graph will
     *             call find which can run in O(n) in the worst case, leading to
     *             O(n^2) for unioning all vertexes, however it's possible
     *             for find to run in slightly under O(n) (still linear runtime) 
     *             in a non-worst-case scenario
     * 
     * 
     * 
     * benefits of quick union:
     *      -   the union function only runs in O(n) in the worst case
     * 
     * 
     * issues with quick union:
     *      -   the find function no longer runs in constant time
     *      -   the union function doesn't try to balance the tree
     *      -   it's possible that the union function will unbalance the
     *          tree and cause subsequent calls to the find function
     *          to run in linear time ( O(n) )
     *      -   given a base case of 3 vertexs, unioning 3 vertexes
     *          will either produce a linked list or a balanced tree:
    *               -    the union function will create a linked list when 
                        the smaller tree is the x value and the larger tree is 
                        the y value             
                    -    the union function will create a minimum height tree
                        when the x value is a larger tree than the y value
    */
    class QuickUnion {
        int[] root;
        public QuickUnion(int size) {
            root = new int[size];
            for(int i = 0; i < size; i++) {
                root[i] = i;
            }
        }


        /**
         * worst case scenario
         * 0->1->2
         * [1,2,2]
         *  0 1 2
         * 
         * @param x
         * @return
         */
        public int find(int x) {
            while(x != root[x]) {
                x = root[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX != rootY) {
                root[rootY] = rootX;
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }

        public void main(String[] args) throws Exception {
            QuickUnion uf = new QuickUnion(10);
            // 1-2-5-6-7 3-8-9 4
            uf.union(1, 2);
            uf.union(2, 5);
            uf.union(5, 6);
            uf.union(6, 7);
            uf.union(3, 8);
            uf.union(8, 9);
            System.out.println(uf.connected(1, 5)); // true
            System.out.println(uf.connected(5, 7)); // true
            System.out.println(uf.connected(4, 9)); // false
            // 1-2-5-6-7 3-8-9-4
            uf.union(9, 4);
            System.out.println(uf.connected(4, 9)); // true
        }
    }

    /**
     * implementation of union by rank of the disjoint set.
     *  
     * this is an optimization of the union function of
     * quick union
     * 
     * This implementation is efficient because it balances the 
     * tree by ensure the height of the tree (h) is closer to 
     * log(n) instead of n
     * 
     * We accomplish balancing the tree by connecting the root
     * of the shorter tree to the root of the taller tree
     *
     * 
     * TC: 
     *      constructor: O(n)
     *      find:        O(log n)
     *      union:       O(log n)
     *      connected:   O(log n)
     * 
     * - For the find operation, in the worst-case scenario, 
     *   when we repeatedly union components of equal rank, 
     *   the tree height will be at most log(n) + 1, so 
     *   the find operation requires O(log n) time
     * - For union and connected, they also run in 
     *   O(log n) since they're bounded by the 
     *   runtime of the find function
     * 
     * 
     * SC:
     *      O(n) to store root, ranks arrays of size n
     * 
     * 
     * 
     * benefits of quick union by rank:
     *      -   the union function balances the tree,
     *          preventing it from becoming a linked list
     *          and essentially keeping the height of the 
     *          tree h = log(n) = O(h) = O(logn) instead of O(h) = O(n)
     *      -   the find function will run in log(n) time now
     *          because it will never produce a linked list
     *          since the tree is balanced after every union
     * 
     * 
     */
    class QuickUnionByRank {
        int[] root;
        int[] rank;

        public QuickUnionByRank(int size) {
            root = new int[size];
            rank = new int[size];

            for(int i = 0; i < size; i++) {
                root[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            while(x != root[x]) {
                x = root[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if(rootX != rootY) {
                if(rank[rootX] > rank[rootY]) {
                    root[rootY] = rootX;
                } else if(rank[rootX] < rank[rootY]) {
                    root[rootX] = rootY;
                } else {
                    root[rootY] = rootX;
                    rank[rootX] += 1;
                }
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }

        public void main(String[] args) throws Exception {
            QuickUnionByRank uf = new QuickUnionByRank(10);
            // 1-2-5-6-7 3-8-9 4
            uf.union(1, 2);
            uf.union(2, 5);
            uf.union(5, 6);
            uf.union(6, 7);
            uf.union(3, 8);
            uf.union(8, 9);
            System.out.println(uf.connected(1, 5)); // true
            System.out.println(uf.connected(5, 7)); // true
            System.out.println(uf.connected(4, 9)); // false
            // 1-2-5-6-7 3-8-9-4
            uf.union(9, 4);
            System.out.println(uf.connected(4, 9)); // true
        }
    }

    /*
    * 
    * TC:
    *      constructor:    O(n)
    *      find:           O(log n)
    *      union:          O(log n)
    *      connected:      O(log n)
    * 
    * SC:
    *      O(n) to store root and rank arrays
    *      and potentially n stack space
    * 
    * benefits of path compression:
    *      -   we save time in the find function by
    *          updating the parent node of all traveresed
    *          nodes to their root node
    *      -   when we search for the root node again
    *          we only need to traverse two elements
    *          to find its root node, which is highly efficient
    *          
    * 
    * issues with path compression:
    *      -   this approach still uses the union function
    *          found in union find, which has the possiblity
    *          of creating a tree that is a linked list
    *          and causing the find function to run in 
    *          O(n) in the worst case
    * 
    * 
    * 
    */
    class PathCompression {
        int[] root;
        public PathCompression(int size) {
            root = new int[size];
            for(int i = 0; i < size; i++) {
                root[i] = i;
            }
        }

        public int find(int x) {
            if(x == root[x]) {
                return x;
            } else {
                return root[x] = find(root[x]);
            }
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if(rootX != rootY) {
                root[rootY] = rootX;
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }

        public void main(String[] args) throws Exception {
            PathCompression uf = new PathCompression(10);
            // 1-2-5-6-7 3-8-9 4
            uf.union(1, 2);
            uf.union(2, 5);
            uf.union(5, 6);
            uf.union(6, 7);
            uf.union(3, 8);
            uf.union(8, 9);
            System.out.println(uf.connected(1, 5)); // true
            System.out.println(uf.connected(5, 7)); // true
            System.out.println(uf.connected(4, 9)); // false
            // 1-2-5-6-7 3-8-9-4
            uf.union(9, 4);
            System.out.println(uf.connected(4, 9)); // true
        }
    }

    /**
     *  TC:
     *      constructor:    O(n)
     *      find:           O(log n)
     *      union:          O(log n)
     *      connected:      O(log n)
     * 
     * SC:
     *      O(n) to store root and rank arrays
     * 
     * 
     * benefits of path compression with union rank:
     *      -   we save time in the find function by
     *          updating the parent node of all traveresed
     *          nodes to their root node
     *      -   when we search for the root node again
     *          we only need to traverse two elements
     *          to find its root node, which is highly efficient
     *      -   the union function balances the tree,
     *          preventing it from becoming a linked list
     *          and essentially keeping the height of the 
     *          tree h = log(n) = O(h) = O(logn) instead of O(h) = O(n)
     *      -   find function will run in log(n) time now
     *          because it will never produce a linked list
     *          since the tree is balanced after every union
     * 
     * issues with path compression with union rank:
     * 
     * 
     */
    public class PathCompressionAndRank {
        private int[] root;
        // Use a rank array to record the height of each vertex, i.e., the "rank" of each vertex.
        private int[] rank;

        public PathCompressionAndRank(int size) {
            root = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                root[i] = i;
                rank[i] = 1; // The initial "rank" of each vertex is 1, because each of them is
                            // a standalone vertex with no connection to other vertices.
            }
        }

        // The find function here is the same as that in the disjoint set with path compression.
        public int find(int x) {
            if (x == root[x]) {
                return x;
            }
            return root[x] = find(root[x]);
        }

        // The union function with union by rank
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    root[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    root[rootX] = rootY;
                } else {
                    root[rootY] = rootX;
                    rank[rootX] += 1;
                }
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
        public void main(String[] args) throws Exception {
            PathCompressionAndRank uf = new PathCompressionAndRank(10);
            // 1-2-5-6-7 3-8-9 4
            uf.union(1, 2);
            uf.union(2, 5);
            uf.union(5, 6);
            uf.union(6, 7);
            uf.union(3, 8);
            uf.union(8, 9);
            System.out.println(uf.connected(1, 5)); // true
            System.out.println(uf.connected(5, 7)); // true
            System.out.println(uf.connected(4, 9)); // false
            // 1-2-5-6-7 3-8-9-4
            uf.union(9, 4);
            System.out.println(uf.connected(4, 9)); // true
        }
    }

    public static void main(String[] args) {
        DisjointSet disjointSet = new DisjointSet();
        QuickFind quickFind = disjointSet.new QuickFind(10);
        QuickUnion quickUnion = disjointSet.new QuickUnion(10);
        QuickUnionByRank quickUnionByRank = disjointSet.new QuickUnionByRank(10);
        PathCompression pathCompression = disjointSet.new PathCompression(10);
        PathCompressionAndRank pathCompressionAndRank = disjointSet.new PathCompressionAndRank(10);

    }
}
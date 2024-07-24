package dsa.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DepthFirstSearch {

    static TreeNode getRoot() {
        /**
         * [10,5,15,3,7,null,18]
         *  0  1  2 3 4  5    6
         * 
         * 
         * 
                    10
                    / \
                   5  15
                  / \   \
                 3   7   18
         */
        TreeNode n0 = new TreeNode(10);
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(15);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(7);
        TreeNode n5 = new TreeNode();
        TreeNode n6 = new TreeNode(18);

        n0.left  = n1;
        n0.right = n2;
        
        n1.left  = n3;
        n1.right = n4;

        n2.left = null;
        n2.right = n6;

        return n0;
    }
    
    static void processNode(TreeNode node) {
        System.out.println(node.val);
    }

}

class DFSPreOrder {
    /**
     * TC: O(n)
     * SC:
     * - worst case: O(n) if tree is a linked list
     * - average case: O(log n) == O(h), where h is height of tree
     * 
     * @param root
     */
    void dfsRecursive(TreeNode root) {
        if(root == null) {
            return;
        }
        DepthFirstSearch.processNode(root);
        dfsRecursive(root.left);
        dfsRecursive(root.right);
    }

    /**
     * TC: O(n)
     * SC:
     * - worst case: O(n) if tree is a linked list
     * - average case: O(log n) == O(h), where h is height of tree
     * 
     * @param root
     */
    void dfsIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node != null) {
                DepthFirstSearch.processNode(node);
                stack.push(node.right);
                stack.push(node.left);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = DepthFirstSearch.getRoot();
        DFSPreOrder preOrder = new DFSPreOrder();
        preOrder.dfsRecursive(root);
        preOrder.dfsIterative(root);
    }
}

class DFSInOrder {
    /**
     * TC: O(n)
     * SC:
     * - worst case: O(n) if tree is a linked list
     * - average case: O(log n)
     * 
     * @param root
     */
    void dfsRecursive(TreeNode root) {
        if(root == null) {
            return;
        }
        dfsRecursive(root.left);
        DepthFirstSearch.processNode(root);
        dfsRecursive(root.right);
    }

    /**
     * TC: O(n)
     * SC:
     * - worst case: O(n) if tree is a linked list
     * - average case: O(log n)
     * 
     * @param root
     */
    void dfsIterative(TreeNode root) {
        if (root == null)
        return;

    Stack<TreeNode> stack = new Stack<>();
    TreeNode current = root;

    while (current != null || !stack.isEmpty()) {
        // Reach the leftmost node of the current subtree
        while (current != null) {
            stack.push(current);
            current = current.left;
        }

            // Current is null, we have reached leftmost node
            current = stack.pop();
            DepthFirstSearch.processNode(current); // Process the current node

            // Move to the right subtree
            current = current.right;
        }
    }
    public static void main(String[] args) {
        TreeNode root = DepthFirstSearch.getRoot();
        DFSInOrder inOrder = new DFSInOrder();
        inOrder.dfsRecursive(root);
        inOrder.dfsIterative(root);
    }
}

class DFSFindAllPaths {
    public List<List<Integer>> findAllPaths(int[][] adjList) {
        List<List<Integer>> paths = new LinkedList<>();
        if(adjList == null || adjList.length == 0) {
            return paths;
        }
        dfsRecursive(adjList, 0, new LinkedList<>(), paths);
        return paths;
    }
    public void dfsRecursive(int[][] adjList, int node, List<Integer> path, List<List<Integer>> paths) {
        path.add(node);
        if(node == adjList.length-1) {
            paths.add(new LinkedList<>(path));
            return; 
        }
        for(int neighbor : adjList[node]) {
            dfsRecursive(adjList, neighbor, path, paths);
            path.remove(path.size() - 1);
        }
        return;
    }

    public List<List<Integer>> dfsIterative(int[][] adjList) {
        /**
         * Use a stack to store the paths.
         * 1. add a path with the source node to the stack.
         * 2. while the stack is not empty:
         *      2.1 check if the last node in the stack is the destination node.
         *          if it is, add the path to the paths list 
         *      2.2 else: iterate through all of neighbors of the last node 
         *                in the current list, append the each neighbor to a new path
         *                and push the new path to the stack
         */
        List<List<Integer>> paths = new LinkedList<>();
        Stack<List<Integer>> stack = new Stack<>();
        stack.push(new LinkedList<>());
        stack.peek().add(0);

        while(!stack.isEmpty()) {
            List<Integer> path = stack.pop();
            int currNode = path.get(path.size() - 1);
            if(currNode == adjList.length - 1) {
                paths.add(new LinkedList<>(path));
            } else {
                for(int neighbor : adjList[currNode]) {
                    List<Integer> newPath = new LinkedList<>(path);
                    newPath.add(neighbor);
                    stack.push(newPath);
                }
            }
        }
        return paths;
    }

    public static void main(String[] args) {
        DFSFindAllPaths dfs = new DFSFindAllPaths();
        int[][] graph = new int[][] {{4,3,1},{3,2,4},{3},{4},{}};
        List<List<Integer>> paths = dfs.findAllPaths(graph);
        paths = dfs.dfsIterative(graph);
        System.out.println(paths);
    }
}
//  Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
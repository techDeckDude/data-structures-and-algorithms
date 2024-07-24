package dsa.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import dsa.store.graph.Graph;

/**
 * Solves the “shortest path” problem in “unweighted graphs”.
 * 
 */
public class BreadthFirstSearch {
    boolean bfs(Graph graph, int startNode, int destinationNode) {
        Set<Integer> seen = new HashSet<Integer>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);

        while(!queue.isEmpty()) {
            int currNode = queue.poll();
            if(currNode == destinationNode) {
                return true;
            }
            if(!seen.contains(currNode)) {
                seen.add(currNode);
                List<Integer> neighbors = graph.getNeighbors(currNode);
                for(int neighbor : neighbors) {
                    queue.add(neighbor);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] edges = new int[3][2];
        edges[0] = new int[]{0,1};
        edges[1] = new int[]{1,2};
        edges[2] = new int[]{2,3};
        Graph graph = new Graph(edges);
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        boolean isPath = bfs.bfs(graph, 0, 3);
        System.out.println(isPath);
    }
}

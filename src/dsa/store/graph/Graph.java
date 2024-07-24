package dsa.store.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    int[][] edges;
    /**
     * build a graph using a hashmap.
     * provide a an array of "edges"
     * where each index in the edges 
     * array is an edge connecting two
     * nodes: [u,v]
     * 
     * the caller should specify if the graph
     * is directed or undericted
     * 
     * @param edges
     * @return
     */
    public Graph(int[][] edges, boolean isDirected) {
        this.edges = edges;
        if(isDirected) {
            buildUndirectedGraph();
        } else {
            buildDirectedGraph();
        }
    }

    private void buildUndirectedGraph() {
        for(int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            if(!graph.containsKey(a)) {
                graph.put(a, new LinkedList<>());
            }
            if(!graph.containsKey(b)) {
                graph.put(b, new LinkedList<>());
            }
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
    }
    
    private void buildDirectedGraph() {
        for(int[] edge : edges) {
            int source = edge[0];
            int destination = edge[1];
            if(!graph.containsKey(source)) {
                graph.put(source, new LinkedList<>());
            }
            if(!graph.containsKey(destination)) {
                graph.put(destination, new LinkedList<>());
            }
            graph.get(source).add(destination);
        }

    }

    /**
     * Takes list of edges as input.
     * A list of edges is always represented
     * as a list of arrays containing vertexes 
     * that are connected to each other
     * 
     * @param edges
     * @param numOfVertecies
     */
    public ArrayList<ArrayList<Integer>> buildAdjacencyList(int[][] edges, int numOfVertecies) {
        ArrayList<ArrayList<Integer>> adjacency_list = new ArrayList<>();        
        /**
         * each each index in the adjacency list is a vertex and the 
         * graph and the list at each index holds the neighbors
         * of that vertex
         * */
         for (int i = 0; i < numOfVertecies; i++) {
            adjacency_list.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            adjacency_list.get(edge[0]).add(edge[1]);
            adjacency_list.get(edge[1]).add(edge[0]);
        }
        return adjacency_list;
    }

    public List<Integer> getNeighbors(int node) {
        return graph.get(node);
    }

    public static void main(String[] args) {
        int[][] edges = new int[3][2];
        edges[0] = new int[]{0,1};
        edges[1] = new int[]{0,2};
        edges[2] = new int[]{2,3};
        Graph directedGraph = new Graph(edges, false);
        Graph undirectedGraph = new Graph(edges, true);

    }
}

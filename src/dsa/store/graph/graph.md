# Graph

## Graph Representations

**List of edges**

- 2D array representing edges, where each edge is a path between two nodes
    - _edges_ -> [[0,1],[1,2],[2,0]]
    - the length of edges[i] == 2, always
    - the index of each edge **_does not_** represent the node that the verticies are connected to
    - **_hint_**: in a graph problem look at the contraints on the size of edges[i] to determine if it's a list of edges or a list of a node's neighbors


**Adjacency List**

- 2D array representing a graph where edges[i] is a list of node i's neighbors (i.e i's edges)
    - _edges_ -> [[1,2], [3], [3], []] <br> 
    - _nodes_ ->    0     1    2    3
    - the length of edges[i] -> 0 <= edges[i] < n, where n is the number of nodes
    - **_hint_**: in a graph problem look at the contraints on the size of edges[i] to determine if it's a list of edges or a list of a node's neighbors

## Graph Types

**Cyclic**

**Acyclic**

_DAG_
- Directed Acyclic Graph

**Directed**

**Undirected**

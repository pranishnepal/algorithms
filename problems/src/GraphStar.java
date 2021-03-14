import java.util.ArrayList;

public class GraphStar {
    /**
     * Problem source: https://leetcode.com/problems/find-center-of-star-graph/
     *
     * Given a graph represented by a 2D-array, find the center node: the node that has an edge with all other nodes.
     * Sample Input: [[1,2],[2,3],[4,2]], Center node = 2; Vertex 2 is connected to all other nodes
     *
     * @param edges: int: 2D-array of integers of the form [[u,v]], where [u,v] represents the edge from u to v.
     * @return int: the center node of this star graph
     */
    public static int findCenter(int[][] edges) {

        int N = edges.length + 1;

        /* Build an adjacency list */
        ArrayList<Integer> adjList[] = new ArrayList[N + 1];

        for (int currEdgeIdx = 0; currEdgeIdx < edges.length; currEdgeIdx++) {
            int vertex_u = edges[currEdgeIdx][0];
            int vertex_v = edges[currEdgeIdx][1];

            /* Initialize current list if uninitialized before */
            adjList[vertex_u] = (adjList[vertex_u] == null) ? new ArrayList<>() : adjList[vertex_u];
            adjList[vertex_v] = (adjList[vertex_v] == null) ? new ArrayList<>() : adjList[vertex_v];

            /* Since the graph is a undirected graph, add edges to both nodes, u and v*/
            adjList[vertex_u].add(vertex_v);
            adjList[vertex_v].add(vertex_u);
        }

        //Traverse through the adjacency list and find the center node:
        for (int possibleCenterNode = 1; possibleCenterNode < adjList.length; possibleCenterNode++) {
            ArrayList<Integer> neighborNodes = adjList[possibleCenterNode];
            if (neighborNodes.size() == N - 1)
                return possibleCenterNode;
        }

        return -1; //unsuccessful to find a star node

    }

}
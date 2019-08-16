package com.algorithmics.ds.graphs;

/**
 * 
 * A graph with no direction
 *
 */
public class UndirectedGraph extends DirectedGraph {
    @Override
    public void addEdge(int v, int w) {
        super.addEdge(v, w);
        super.addEdge(w, v);
    }
}

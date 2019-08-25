package com.algorithmics.ds.graphs;

/**
 * 
 * A graph with no direction
 *
 */
public class UndirectedGraph extends DirectedGraph {
    @Override
    public boolean addEdge(int v, int w) {
        if (!hasEdge(v, w)) {
            super.addEdge(v, w);
            super.addEdge(w, v);
            return true;
        } else {
            return false;
        }

    }
}

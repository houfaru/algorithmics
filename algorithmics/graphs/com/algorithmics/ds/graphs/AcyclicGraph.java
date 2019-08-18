package com.algorithmics.ds.graphs;

/**
 * 
 * A strictly directed graph which does not allow cycle.
 *
 */
public class AcyclicGraph extends DirectedGraph {
    @Override
    public void addEdge(int v, int w) {
        if (canReach(w, v)) {
            throw new RuntimeException("Partial Order cannot have cycle");
        }
        super.addEdge(v, w);
    }

}

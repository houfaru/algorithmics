package com.algorithmics.ds.graphs;

/**
 * 
 * A strictly directed graph which does not allow cycle.
 *
 */
public class AcyclicGraph extends DirectedGraph {
    @Override
    public boolean addEdge(int v, int w) {
        if (!hasEdge(v, w) && !canReach(w, v)) {
            super.addVertex(v);
            super.addVertex(w);
            super.addEdge(v, w);
            return true;
        } else {
            return false;
        }

    }

}

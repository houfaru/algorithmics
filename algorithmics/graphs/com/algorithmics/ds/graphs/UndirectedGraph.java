package com.algorithmics.ds.graphs;

import com.algorithmics.np.core.ProblemStructure;

/**
 * 
 * A graph with no direction
 *
 */
public class UndirectedGraph extends DirectedGraph implements ProblemStructure{
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

package com.algorithmics.ds.graphs;

import java.util.HashSet;

/**
 * Graph which allows direction (one direction, both) but no loops
 *
 */
public class DirectedGraph extends AbstractAdjacencyGraph {

    @Override
    public boolean addEdge(int v, int w) {
        if (v != w && !hasEdge(v, w)) {
            adjacencyList.putIfAbsent(v, new HashSet<Integer>());
            adjacencyList.get(v).add(w);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String s = "v=" + adjacencyList.keySet().toString();
        s += "e=" + adjacencyList.toString();
        return s;
    }
}

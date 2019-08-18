package com.algorithmics.ds.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Graph which allows:<br>
 * - loop<br>
 * - direction (also both sides)
 *
 */
public class DirectedGraph extends AbstractAdjacencyGraph {
    @Override
    public void addEdge(int v, int w) {
        if (v == w) {
            throw new RuntimeException("Loop is not allowed : (" + v + "," + w + ")");
        }
        adjacencyList.putIfAbsent(v, new HashSet<Integer>());
        adjacencyList.get(v).add(w);
    }

    public List<Integer> getVertices() {
        List<Integer> list = new ArrayList<Integer>(adjacencyList.keySet());
        return list;
    }

    @Override
    public String toString() {
        String s = "v=" + adjacencyList.keySet().toString();
        s += "e=" + adjacencyList.toString();
        return s;
    }
}

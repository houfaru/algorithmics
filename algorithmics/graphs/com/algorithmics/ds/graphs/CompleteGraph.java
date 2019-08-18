package com.algorithmics.ds.graphs;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompleteGraph implements Graph {

    private int numberOfVertices = 0;

    private Set<Integer> vertexSet = null;

    public CompleteGraph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
    }

    @Override
    public boolean containsEdge(int v, int w) {
        if (v < numberOfVertices && w < numberOfVertices) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Set<Integer> getNeighbors(int v) {
        if (vertexSet == null) {
            vertexSet = IntStream.range(0, numberOfVertices).boxed().collect(Collectors.toSet());
        }
        Set<Integer> neighbors = new HashSet<Integer>(vertexSet);
        neighbors.remove(v);
        return neighbors;
    }

    @Override
    public int getNumOfVertices() {
        return numberOfVertices;
    }

    @Override
    public void addEdge(int v, int w) {
        // nothing to do
    }

}

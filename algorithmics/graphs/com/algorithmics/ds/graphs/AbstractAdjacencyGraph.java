package com.algorithmics.ds.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.algorithmics.ds.graphs.algorithms.BFSGraphIterator;

public abstract class AbstractAdjacencyGraph implements Graph {

    protected Map<Integer, HashSet<Integer>> adjacencyList =
            new HashMap<Integer, HashSet<Integer>>();

    public boolean canReach(int sourceVertex, int destinationVertex) {

        final BFSGraphIterator bfsIterator = new BFSGraphIterator(this, sourceVertex);

        while (bfsIterator.hasNext()) {
            int nextVertex = bfsIterator.next();
            if (nextVertex == destinationVertex) {
                return true;
            }
        }
        return false;
    }

    public boolean containsVertex(int i) {
        return adjacencyList.keySet().contains(i);
    }

    public void addVertex(int v) {
        adjacencyList.putIfAbsent(v, new HashSet<Integer>());
    }

    @Override
    public boolean containsEdge(int v, int w) {
        final HashSet<Integer> neighbors = adjacencyList.get(v);
        return null != neighbors && neighbors.contains(w);
    }

    @Override
    public Set<Integer> getNeighbors(int v) {
        final HashSet<Integer> neighbors = adjacencyList.get(v);
        return null == neighbors ? new HashSet<>() : new HashSet<Integer>(neighbors);
    }

    @Override
    public int getNumOfVertices() {
        return adjacencyList.keySet().size();
    }

    public HashMap<Integer, HashSet<Integer>> getAdjacencyList() {
        return new HashMap<Integer, HashSet<Integer>>(adjacencyList);
    }



}

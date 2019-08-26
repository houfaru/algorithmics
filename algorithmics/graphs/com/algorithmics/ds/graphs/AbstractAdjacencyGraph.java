package com.algorithmics.ds.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

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

    public void copyEdgesFrom(Graph g) {
        g.getEdges().forEach(e -> addEdge(e.getFrom(), e.getTo()));
    }

    public boolean containsVertex(int i) {
        return adjacencyList.keySet().contains(i);
    }

    public void addVertex(int v) {
        adjacencyList.putIfAbsent(v, new HashSet<Integer>());
    }

    @Override
    public boolean hasEdge(int v, int w) {
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

    @Override
    public Stream<Integer> getNodes() {
        return adjacencyList.keySet().stream();
    }

    @Override
    public Stream<Edge> getEdges() {
        return adjacencyList.entrySet().stream()
                .flatMap(e -> e.getValue().stream().map(v -> new Edge(e.getKey(), v)));
    }

}

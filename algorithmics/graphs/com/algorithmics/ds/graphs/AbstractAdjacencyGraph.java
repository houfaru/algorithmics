package com.algorithmics.ds.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public abstract class AbstractAdjacencyGraph implements Graph {

    protected HashMap<Integer, HashSet<Integer>> adjacencyList =
            new HashMap<Integer, HashSet<Integer>>();

    public boolean canReach(int sourceVertex, int destinationVertex) {

        Stack<Integer> remainingVertices = new Stack<>();
        Set<Integer> visited = new HashSet<>();

        remainingVertices.push(sourceVertex);
        while (!remainingVertices.isEmpty()) {
            Integer currentVertex = remainingVertices.pop();

            if (visited.contains(currentVertex)) {
                continue;
            }

            if (currentVertex == destinationVertex) {
                return true;
            }
            visited.add(currentVertex);
            Set<Integer> neighbors = getNeighbors(currentVertex);
            neighbors.removeAll(visited);
            remainingVertices.addAll(neighbors);
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
        HashSet<Integer> neighbors = adjacencyList.get(v);
        return null != neighbors && neighbors.contains(w);
    }

    @Override
    public Set<Integer> getNeighbors(int v) {
        HashSet<Integer> neighbors = adjacencyList.get(v);
        return null == neighbors ? new HashSet<>() : new HashSet<Integer>(neighbors);
    }

    public HashMap<Integer, HashSet<Integer>> getAdjacencyList() {
        return new HashMap<Integer, HashSet<Integer>>(adjacencyList);
    }

    @Override
    public int getNumOfVertices() {
        return adjacencyList.keySet().size();
    }


}

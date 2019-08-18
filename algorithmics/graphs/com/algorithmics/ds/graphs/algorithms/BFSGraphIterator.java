package com.algorithmics.ds.graphs.algorithms;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import com.algorithmics.ds.graphs.Graph;

public class BFSGraphIterator implements Iterator<Integer> {

    private final Graph graph;
    private final Queue<Integer> queue;
    private final Set<Integer> visitedVertices;

    public BFSGraphIterator(Graph graph, int initialVertex) {
        this.graph = graph;
        queue = new LinkedList<>();
        visitedVertices = new HashSet<>();
        queue.add(initialVertex);
    }


    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            final int currentVertex = queue.poll();
            visitedVertices.add(currentVertex);

            Set<Integer> nextUnvisitedNeighbors = graph.getNeighbors(currentVertex);
            nextUnvisitedNeighbors.removeAll(visitedVertices);

            queue.addAll(nextUnvisitedNeighbors);

            return currentVertex;
        } else {
            throw new RuntimeException("Iterator has no more value");
        }
    }

}

package com.algorithmics.ds.graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class WeightedGraph<T extends Graph> implements Graph {

    private Map<Edge, Double> weights = new HashMap<>();

    private T graph;

    public WeightedGraph(T graph) {
        this.graph = graph;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        return graph.hasEdge(v, w);
    }

    @Override
    public Set<Integer> getNeighbors(int v) {
        return graph.getNeighbors(v);
    }

    @Override
    public int getNumOfVertices() {
        return graph.getNumOfVertices();
    }

    @Override
    public boolean addEdge(int v, int w) {
        return graph.addEdge(v, w);
    }

    @Override
    public Stream<Integer> getNodes() {
        return graph.getNodes();
    }

    public void addEdge(int v, int w, double weight) {
        graph.addEdge(v, w);
        Edge pair = new Edge(v, w);
        weights.put(pair, weight);
        if (graph instanceof UndirectedGraph) {
            Edge reversePair = new Edge(w, v);
            weights.put(reversePair, weight);
        }
    }

    public double getWeight(int v, int w) {
        Edge pair = new Edge(v, w);
        return weights.computeIfAbsent(pair, k -> 0d);
    }

    public void setWeight(int v, int w, double weight) {
        if (hasEdge(v, w)) {
            Edge pair = new Edge(v, w);
            weights.put(pair, weight);
        }
    }

    @Override
    public void addVertex(int v) {
        graph.addVertex(v);
    }

    @Override
    public String toString() {
        return weights.toString();
    }

}

package com.algorithmics.ds.graphs;

import java.util.Map;
import java.util.Set;
import javafx.util.Pair;

public class WeightedGraph implements Graph {

    private Map<Pair<Integer, Integer>, Double> weights;

    private Graph graph;

    public WeightedGraph(Graph graph) {
        this.graph = graph;
    }

    @Override
    public boolean containsEdge(int v, int w) {
        return graph.containsEdge(v, w);
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
    public void addEdge(int v, int w) {
        graph.addEdge(v, w);
    }

    public void addEdge(int v, int w, double weight) {
        graph.addEdge(v, w);
        Pair<Integer, Integer> pair = new Pair<Integer, Integer>(v, w);
        weights.put(pair, weight);
    }

    public double getWeight(int v, int w) {
        Pair<Integer, Integer> pair = new Pair<Integer, Integer>(v, w);
        return weights.computeIfAbsent(pair, k -> 0d);
    }

    public void setWeight(int v, int w, double weight) {
        if (!containsEdge(v, w)) {
            throw new RuntimeException("adding weight to nonexisting edge");
        }
        Pair<Integer, Integer> pair = new Pair<Integer, Integer>(v, w);
        weights.put(pair, weight);
    }
}

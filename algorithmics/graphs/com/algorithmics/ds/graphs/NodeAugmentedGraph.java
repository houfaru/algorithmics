package com.algorithmics.ds.graphs;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public abstract class NodeAugmentedGraph<G extends Graph, T> implements Graph {

    private G decoratedGraph;

    private Map<Integer, T> augmentedInformation;

    public NodeAugmentedGraph(G decoratedGraph) {
        this.decoratedGraph = decoratedGraph;
    }

    protected T getAugmentedInformation(int v) {
        return augmentedInformation.get(v);
    }

    protected void setAugmentedInformation(int v, T t) {
        augmentedInformation.put(v, t);
    }

    @Override
    public boolean hasEdge(int v, int w) {
        return decoratedGraph.hasEdge(v, w);
    }

    @Override
    public Set<Integer> getNeighbors(int v) {
        return decoratedGraph.getNeighbors(v);
    }

    @Override
    public int getNumOfVertices() {
        return decoratedGraph.getNumOfVertices();
    }

    @Override
    public boolean addEdge(int v, int w) {
        return decoratedGraph.addEdge(v, w);
    }

    @Override
    public Stream<Integer> getNodes() {
        return decoratedGraph.getNodes();
    }

    @Override
    public void addVertex(int v) {
        decoratedGraph.addVertex(v);
    }

    public Graph getGraph() {
        return decoratedGraph;
    }

}

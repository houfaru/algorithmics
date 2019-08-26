package com.algorithmics.ds.graphs;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public abstract class NodeAugmentedGraph<G extends Graph, L> implements Graph {

    private G decoratedGraph;

    private Map<Integer, L> augmentedInformation;

    public NodeAugmentedGraph(G decoratedGraph) {
        this.decoratedGraph = decoratedGraph;
    }

    protected L getAugmentedInformation(int v) {
        return augmentedInformation.get(v);
    }

    protected void setAugmentedInformation(int v, L t) {
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

    @Override
    public Stream<Edge> getEdges() {
        return decoratedGraph.getEdges();
    }

    public Graph getGraph() {
        return decoratedGraph;
    }

}

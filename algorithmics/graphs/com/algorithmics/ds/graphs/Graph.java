package com.algorithmics.ds.graphs;

import java.util.Set;
import java.util.stream.Stream;

public interface Graph {

    public boolean hasEdge(int v, int w);

    public Set<Integer> getNeighbors(int v);

    public int getNumOfVertices();

    public void addVertex(int v);

    public boolean addEdge(int v, int w);

    public Stream<Integer> getNodes();
    
    public Stream<Edge> getEdges();

}

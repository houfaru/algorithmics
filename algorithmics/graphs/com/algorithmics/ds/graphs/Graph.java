package com.algorithmics.ds.graphs;

import java.util.Set;

public interface Graph {

    public boolean containsEdge(int v, int w);

    public Set<Integer> getNeighbors(int v);

    public int getNumOfVertices();
    
    public void addEdge(int v, int w);

}

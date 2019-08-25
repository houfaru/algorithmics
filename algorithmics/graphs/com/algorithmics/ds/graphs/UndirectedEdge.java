package com.algorithmics.ds.graphs;

public class UndirectedEdge extends Edge {
    public UndirectedEdge(int v, int w) {
        super(Math.min(v, w), Math.max(v, w));
    }

}

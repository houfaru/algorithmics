package com.algorithmics.ds.graphs;

public class Edge {
    private final int v;
    private final int w;

    public Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }

    public int getFrom() {
        return v;
    }

    public int getTo() {
        return w;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Edge)) {
            return false;
        }
        Edge other = (Edge) obj;
        return this.v == other.v && this.w == other.w;
    }

    @Override
    public String toString() {

        return "(" + v + "," + w + ")";
    }
}

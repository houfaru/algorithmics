package com.algorithmics.np.vc.instance;

import java.util.HashSet;

import com.algorithmics.ds.graphs.UndirectedGraph;
import com.algorithmics.np.core.NPHardProblem;

public class VertexCoverInstance implements NPHardProblem<VertexCover> {
    private final UndirectedGraph plainGraph;
    private final int k;

    public VertexCoverInstance(UndirectedGraph g, int k) {
        this.k = k;
        this.plainGraph = g;
    }

    public int getK() {
        return k;
    }

    public UndirectedGraph getPlainGraph() {
        return plainGraph;
    }

    @Override
    public boolean verify(VertexCover certificate) {
        if (certificate.getVertices().size() > k) {
            return false;
        }
        HashSet<Integer> coveredNode = new HashSet<>();
        for (Integer vertex : certificate.getVertices()) {
            if (plainGraph.containsVertex(vertex)) {
                coveredNode.add(vertex);
                coveredNode.addAll(plainGraph.getNeighbors(vertex));
            }
            if (plainGraph.getNumOfVertices() == coveredNode.size()) {
                return true;
            }
        }
        return false;
    }
}

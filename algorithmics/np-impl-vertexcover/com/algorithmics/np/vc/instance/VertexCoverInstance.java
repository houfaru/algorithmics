package com.algorithmics.np.vc.instance;

import java.util.function.Predicate;

import com.algorithmics.ds.graphs.Edge;
import com.algorithmics.ds.graphs.UndirectedGraph;
import com.algorithmics.np.core.NPHardProblem;

public class VertexCoverInstance implements NPHardProblem<VertexCover> {

    private final UndirectedGraph undirectedGraph;
    private final int k;

    public VertexCoverInstance(UndirectedGraph undirectedGraph, int k) {
        this.k = k;
        this.undirectedGraph = undirectedGraph;
    }

    public int getK() {
        return k;
    }

    public UndirectedGraph getPlainGraph() {
        return undirectedGraph;
    }

    @Override
    public boolean verify(VertexCover certificate) {
        if (certificate.getVertices().size() > k) {
            return false;
        } else {
            Predicate<Edge> isCovered = edge -> certificate.contains(edge.getFrom())
                    || certificate.contains(edge.getTo());
            return undirectedGraph.getEdges().allMatch(isCovered);
        }


    }


}

package com.algorithmics.ds.graphs.algorithms;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.algorithmics.ds.graphs.AcyclicGraph;

public class GraphUtil {
    static Set<Integer> visitedVertices = new HashSet<>();

    private static Stack<Integer> topologicalSort(int v, AcyclicGraph graph, Stack<Integer> stack) {
        visitedVertices.add(v);
        final List<Integer> vertices = graph.getVertices();
        for (Integer vertex : vertices) {
            if (!visitedVertices.contains(vertex)) {
                topologicalSort(vertex, graph, stack);
            }
        }
        stack.push(v);
        return stack;
    }

    public static Stack<Integer> topologicalSort(AcyclicGraph graph) {
        final Stack<Integer> stack = new Stack<>();
        visitedVertices = new HashSet<>();
        for (Integer vertex : graph.getVertices()) {
            if (!visitedVertices.contains(vertex)) {
                topologicalSort(vertex, graph, stack);
            }
        }
        return stack;
    }

}

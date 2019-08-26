package com.algorithmics.ds.graphs.algorithms;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

import com.algorithmics.ds.graphs.AcyclicGraph;
import com.algorithmics.ds.graphs.CompleteGraph;
import com.algorithmics.ds.graphs.DirectedGraph;
import com.algorithmics.ds.graphs.Graph;
import com.algorithmics.ds.graphs.UndirectedGraph;
import com.algorithmics.ds.graphs.WeightedGraph;

public class Graphs {
    static Set<Integer> visitedVertices = new HashSet<>();

    private static Stack<Integer> topologicalSort(int v, AcyclicGraph graph, Stack<Integer> stack) {
        visitedVertices.add(v);

        graph.getNodes().forEach(vertex -> {
            if (!visitedVertices.contains(vertex)) {
                topologicalSort(vertex, graph, stack);
            }
        });

        stack.push(v);
        return stack;
    }

    public static List<Integer> topologicalSort(AcyclicGraph graph) {
        final Stack<Integer> stack = new Stack<>();
        visitedVertices = new HashSet<>();
        graph.getNodes().forEach(vertex -> {
            if (!visitedVertices.contains(vertex)) {
                topologicalSort(vertex, graph, stack);
            }
        });
        return stack;
    }

    /**
     * Floyd Warshall Algorithm to compute the transitiveClosure of a Graph
     * 
     * @param graph
     * @return
     */
    public static DirectedGraph transitiveClosure(DirectedGraph graph) {
        DirectedGraph transitiveClosure = new DirectedGraph();
        transitiveClosure.copyEdgesFrom(graph);
        int numOfVertices = transitiveClosure.getNumOfVertices();
        for (int k = 0; k < numOfVertices; k++) {
            for (int i = 0; i < numOfVertices; i++) {
                for (int j = 0; j < numOfVertices; j++) {
                    if (!transitiveClosure.hasEdge(i, j) && transitiveClosure.hasEdge(i, k)
                            && transitiveClosure.hasEdge(k, j)) {
                        transitiveClosure.addEdge(i, j);
                    }
                }
            }
        }
        return transitiveClosure;
    }


    /**
     * G(n,p) generation
     * 
     * @param n
     * @return
     */
    public static <T extends Graph> T generateGnp(Class<T> clazz, int n, double p) {

        if (clazz.equals(CompleteGraph.class)) {
            throw new RuntimeException(
                    "There is no need to create generate Complete graph with G(n,p) model");
        }
        try {
            final T graph = clazz.newInstance();
            final Random r = new Random();
            for (int fromVertex = 0; fromVertex < n; fromVertex++) {
                graph.addVertex(fromVertex);
                for (int toVertex = 0; toVertex < n; toVertex++) {
                    if (r.nextGaussian() < p) {
                        graph.addVertex(toVertex);
                        graph.addEdge(fromVertex, toVertex);
                    }
                }
            }
            return graph;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T extends Graph> WeightedGraph<T> generateWithWeight(Class<T> clazz, int n,
            double p) {
        final T graph = generateGnp(clazz, n, p);
        final WeightedGraph<T> weightedGraph = new WeightedGraph<>(graph);
        final Random r = new Random();
        for (int fromVertex = 0; fromVertex < n; fromVertex++) {
            for (int toVertex = 0; toVertex < n; toVertex++) {
                weightedGraph.setWeight(fromVertex, toVertex, Math.abs(r.nextGaussian()));
            }
        }
        return weightedGraph;
    }

    public static <T extends Graph> T generateGnm(Class<T> clazz, int n, double numOfEdge)
            throws InstantiationException, IllegalAccessException {
        final T graph = clazz.newInstance();
        int currentNumOfEdge = 0;
        final Random r = new Random();
        for (int i = 0; i < n; i++) {
            graph.addVertex(i);
        }
        while (currentNumOfEdge <= numOfEdge) {
            boolean edgeAdded = graph.addEdge(Math.abs(r.nextInt()) % n, Math.abs(r.nextInt()) % n);
            if (edgeAdded) {
                currentNumOfEdge++;
            }
        }
        return graph;
    }

    public boolean[][] toMatrix(Graph g) {
        int numOfVertices = g.getNumOfVertices();
        boolean[][] matrix = new boolean[numOfVertices][numOfVertices];
        g.getEdges().forEach(e -> matrix[e.getFrom()][e.getTo()] = true);
        return matrix;
    }

    @Test
    public void test() throws InstantiationException, IllegalAccessException {
        System.out.println(generateGnp(AcyclicGraph.class, 20, 0.2));
        System.out.println(generateGnp(DirectedGraph.class, 20, 0.2));
        System.out.println(generateGnp(UndirectedGraph.class, 20, 0.2));
        System.out.println(generateWithWeight(AcyclicGraph.class, 20, 0.2));
        System.out.println(generateGnm(UndirectedGraph.class, 8, 6));

    }

}

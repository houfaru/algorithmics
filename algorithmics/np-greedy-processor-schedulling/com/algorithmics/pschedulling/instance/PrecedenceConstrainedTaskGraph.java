package com.algorithmics.pschedulling.instance;

import java.util.stream.Stream;

import com.algorithmics.ds.graphs.AcyclicGraph;
import com.algorithmics.ds.graphs.NodeAugmentedGraph;

public class PrecedenceConstrainedTaskGraph extends NodeAugmentedGraph<AcyclicGraph, Task> {

    public PrecedenceConstrainedTaskGraph(AcyclicGraph decoratedGraph) {
        super(decoratedGraph);
    }

    public Task getTask(int v) {
        return getAugmentedInformation(v);
    }

    public void setTask(int v, Task t) {
        setAugmentedInformation(v, t);
    }

    public Stream<Task> getTasks() {
        return getNodes().map(this::getTask);
    }

}

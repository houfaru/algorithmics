package com.algorithmics.pschedulling.instance;

import com.algorithmics.npo.core.NPOProblem;

public class PSchedullingInstance implements NPOProblem<TaskAssignment> {
    private final PrecedenceConstrainedTaskGraph graph;
    private final double maxSum;

    public PSchedullingInstance(PrecedenceConstrainedTaskGraph graph, double deadline) {
        super();
        this.graph = graph;
        this.maxSum = deadline;
    }

    public double getDeadline() {
        return maxSum;
    }

    public PrecedenceConstrainedTaskGraph getPrecedenceConstraint() {
        return graph;
    }

    @Override
    public boolean verify(TaskAssignment taskAssignment) {
        return false;
    }

    @Override
    public double opt(TaskAssignment certificate) {
        return graph.getTasks().mapToDouble(t -> t.getProcessingTime() * t.getWeight()).sum();
    }

}

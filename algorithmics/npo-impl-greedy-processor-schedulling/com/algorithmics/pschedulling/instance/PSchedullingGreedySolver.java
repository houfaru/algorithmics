package com.algorithmics.pschedulling.instance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.algorithmics.ds.graphs.AcyclicGraph;
import com.algorithmics.ds.graphs.algorithms.Graphs;
import com.algorithmics.np.core.Solver;

/**
 * 
 * Precedence constrained Tasks Schedulling.
 *
 */
public class PSchedullingGreedySolver implements Solver<PSchedullingInstance, TaskAssignment> {

    @Override
    public Optional<TaskAssignment> solve(PSchedullingInstance tasksWithPrecedenceConstraints) {
        PrecedenceConstrainedTaskGraph partialOrder =
                tasksWithPrecedenceConstraints.getPrecedenceConstraint();
        List<Integer> topologicallyOrderedVertices =
                Graphs.topologicalSort((AcyclicGraph) partialOrder.getGraph());
        List<List<Integer>> totalOrdersDividedByJumps = new ArrayList<>();
        int currentHeadIdx = 0;
        for (int idx = 0; idx < topologicallyOrderedVertices.size() - 1; idx++) {
            Integer vertex1 = topologicallyOrderedVertices.get(idx);
            Integer vertex2 = topologicallyOrderedVertices.get(idx + 1);
            if (!partialOrder.hasEdge(vertex1, vertex2)) {
                totalOrdersDividedByJumps
                        .add(topologicallyOrderedVertices.subList(currentHeadIdx, idx + 1));
                currentHeadIdx = idx + 1;
            } ;
        }

        totalOrdersDividedByJumps.sort((l1, l2) -> Integer.compare(l1.size(), l2.size()));

        return null;
    }

    @Override
    public boolean verify(PSchedullingInstance p, TaskAssignment sc) {
        // TODO Auto-generated method stub
        return false;
    }

}

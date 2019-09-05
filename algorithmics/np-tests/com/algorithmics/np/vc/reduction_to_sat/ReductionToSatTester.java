package com.algorithmics.np.vc.reduction_to_sat;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Test;

import com.algorithmics.ds.graphs.UndirectedGraph;
import com.algorithmics.np.vc.instance.VertexCover;
import com.algorithmics.np.vc.instance.VertexCoverInstance;
import com.algorithmics.np.vc.preprocessing.TGFGraphReader;
import com.algorithmics.np.vc.solver.SATBasedVCSolver;
import com.algorithmics.servicesupport.UserExecutionException;

public class ReductionToSatTester {
    @Test
    public void solveTGF() throws UserExecutionException {
        
        SATBasedVCSolver solver = new SATBasedVCSolver();
        TGFGraphReader reader = new TGFGraphReader();
        UndirectedGraph graph = reader.readFromFile("data/graph.tgf");

        VertexCoverInstance instance = new VertexCoverInstance(graph, 3);
        Optional<VertexCover> solution = solver.solve(instance);
        if (solution.isPresent()) {
            assertTrue(solver.verify(instance, solution.get()));
        } else {
            fail("something wrong");
        }

    }
}

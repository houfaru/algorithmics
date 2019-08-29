package com.algorithmics.invocation;

import java.util.ServiceLoader;

import com.algorithmics.np.core.Solver;
import com.algorithmics.servicesupport.ExecutionException;

public class SolverLocator {
    public static Solver locate(String name) throws ExecutionException {
        ServiceLoader<Solver> solvers = ServiceLoader.load(Solver.class);
        for (Solver solver : solvers) {
            SolverMapping annotation = solver.getClass().getAnnotation(SolverMapping.class);
            if (null != annotation && annotation.name().equals(name)) {
                return solver;
            }

        }
        throw new ExecutionException("Solver not found");
    }
}

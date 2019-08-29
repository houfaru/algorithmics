package com.algorithmics.invocation;

import java.util.ServiceLoader;

import com.algorithmics.np.core.Solver;

public class InvocationHandler {
    public static Solver handle(String name, SolverInvocationContext context) {
        ServiceLoader<Solver> solvers = ServiceLoader.load(Solver.class);
        for (Solver solver : solvers) {
            SolverMapping annotation = solver.getClass().getAnnotation(SolverMapping.class);
            if (null != annotation && annotation.name().equals(name)) {
                return solver;
            }

        }
        return null;
    }
}

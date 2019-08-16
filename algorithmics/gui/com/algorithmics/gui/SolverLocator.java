package com.algorithmics.gui;

import com.algorithmics.np.SAT.solver.SATSolverRecursive;
import com.algorithmics.np.core.Solver;
import com.algorithmics.np.knapsack.solver.DynKnapsackSolver;
import com.algorithmics.np.vc.solver.SATBasedVCSolver;

import algorithmics.minisat.SATSolverSystemCall;

public class SolverLocator {
    
    public static <S extends Solver<?, ?>> S getSolver(Class<S> clazz) {
        
        if (clazz.equals(SATBasedVCSolver.class)) {
            return clazz.cast(new SATBasedVCSolver());
        }
        if (clazz.equals(DynKnapsackSolver.class)) {
            return clazz.cast(new DynKnapsackSolver());
        }
        if (clazz.equals(SATSolverRecursive.class)) {
            return clazz.cast(new SATSolverRecursive());
        }
        if (clazz.equals(SATSolverSystemCall.class)) {
            return clazz.cast(new SATSolverSystemCall());
        }

        return null;
    }
}

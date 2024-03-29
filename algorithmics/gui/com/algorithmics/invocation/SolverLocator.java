package com.algorithmics.invocation;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import com.algorithmics.np.core.NPProblem;
import com.algorithmics.np.core.Solver;
import com.algorithmics.servicesupport.UserExecutionException;

public class SolverLocator {
    public static Solver<?, ?> locate(String name) throws UserExecutionException {
        ServiceLoader<Solver> solvers = ServiceLoader.load(Solver.class);
        for (Solver<?, ?> solver : solvers) {
            SolverMapping annotation = solver.getClass().getAnnotation(SolverMapping.class);
            if (null != annotation && annotation.name().equals(name)) {
                return solver;
            }
        }
        throw new UserExecutionException("Solver not found");
    }

    public static NPProblem locateProblem(String name) throws UserExecutionException {
        ServiceLoader<NPProblem> solvers = ServiceLoader.load(NPProblem.class);
        for (NPProblem problem : solvers) {
            if (problem.getClass().getSimpleName().equals(name)) {
                return problem;
            }
        }
        throw new UserExecutionException("Problem Instance not found");
    }
    
    public static List<NPProblem>getProblems(){
        ServiceLoader<NPProblem> solvers = ServiceLoader.load(NPProblem.class);
        List<NPProblem> list=new ArrayList<>();
        
        for (NPProblem npProblem : solvers) {
            list.add(npProblem);
        }
        return list;
    }
}

package com.algorithmics.np.core;

import java.util.Optional;

import com.algorithmics.np.SAT.instance.VariableAssignment;
import com.algorithmics.np.SAT.instance.CNF.SentenceInCNF;
import com.algorithmics.np.SAT.solver.SATSolverRecursive;
import com.algorithmics.servicesupport.ExecutionException;

public class SatBasedSolver<NP_CERTIFICATE extends Certificate, NP_PROBLEM extends NPProblem<NP_CERTIFICATE>>
        implements Solver<NP_PROBLEM, NP_CERTIFICATE> {

    private Reducer<NP_CERTIFICATE, NP_PROBLEM, VariableAssignment, SentenceInCNF> reducer;
    private SATSolverRecursive satSolver = new SATSolverRecursive();

    public SatBasedSolver(
            Reducer<NP_CERTIFICATE, NP_PROBLEM, VariableAssignment, SentenceInCNF> reducer) {
        super();
        this.reducer = reducer;
    }

    public Optional<NP_CERTIFICATE> solve(NP_PROBLEM p) {
        SentenceInCNF sentence = reducer.reduce(p);

        Optional<VariableAssignment> solve = satSolver.solve(sentence);
        if (solve.isPresent()) {
            return Optional.of(reducer.interpretCertificate(solve.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean verify(NP_PROBLEM p, NP_CERTIFICATE sc) {
        return p.verify(sc);

    }

    @Override
    public NP_PROBLEM getProblem(String string) throws ExecutionException {
        // TODO Auto-generated method stub
        return null;
    };
}

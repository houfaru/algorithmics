package com.algorithmics.np.core;

import java.util.Optional;

import com.algorithmics.np.SAT.instance.VariableAssignment;
import com.algorithmics.np.SAT.instance.CNF.SentenceInCNF;
import com.algorithmics.np.SAT.solver.SATSolverRecursive;

public abstract class SatBasedSolver<P extends NPProblem, C extends Certificate> {

    private Reducer<P, SentenceInCNF> reducer;

    public SatBasedSolver(Reducer<P, SentenceInCNF> reducer) {
        super();
        this.reducer = reducer;
    }

    public Optional<C> solve(P p) {

        SentenceInCNF sentence = reducer.reduce(p);
        SATSolverRecursive satSolver = new SATSolverRecursive();
        Optional<VariableAssignment> solve = satSolver.solve(sentence);

        return interpretCertificate(solve);
    };


    protected abstract Optional<C> interpretCertificate(Optional<VariableAssignment> solve);

    public abstract boolean verify(P p, C sc);


}

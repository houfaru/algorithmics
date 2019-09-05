package com.algorithmics.np.core;

import java.util.Optional;

import com.algorithmics.servicesupport.UserExecutionException;

public interface Solver<NP_PROBLEM extends NPProblem<CERTIFICATE>, CERTIFICATE extends Certificate> {
    public Optional<CERTIFICATE> solve(NP_PROBLEM p) throws UserExecutionException;

    public boolean verify(NP_PROBLEM p, CERTIFICATE sc) throws UserExecutionException;

    public default Optional<CERTIFICATE> solveForDefaultFormat(String string)
            throws UserExecutionException {
        return solve(getProblem(string));
    };

    public NP_PROBLEM getProblem(String string) throws UserExecutionException;

}

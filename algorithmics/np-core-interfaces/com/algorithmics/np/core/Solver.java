package com.algorithmics.np.core;

import java.util.Optional;

import com.algorithmics.servicesupport.ExecutionException;

public interface Solver<NP_PROBLEM extends NPProblem<CERTIFICATE>, CERTIFICATE extends Certificate> {
    public Optional<CERTIFICATE> solve(NP_PROBLEM p) throws ExecutionException;

    public boolean verify(NP_PROBLEM p, CERTIFICATE sc);
    
    public default Optional<CERTIFICATE> solveForDefaultFormat(String string) throws ExecutionException{
        throw new ExecutionException(new UnsupportedOperationException("Algorithm is not yet implemented"));
    };
    
}

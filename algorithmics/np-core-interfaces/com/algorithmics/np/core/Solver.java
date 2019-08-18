package com.algorithmics.np.core;

import java.util.Optional;

public interface Solver<NP_PROBLEM extends NPProblem<CERTIFICATE>, CERTIFICATE extends Certificate> {
    public Optional<CERTIFICATE> solve(NP_PROBLEM p);

    public boolean verify(NP_PROBLEM p, CERTIFICATE sc);
}

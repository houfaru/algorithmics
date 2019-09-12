package com.algorithmics.npo.core;

import com.algorithmics.np.core.Certificate;
import com.algorithmics.np.core.NPHardProblem;
import com.algorithmics.servicesupport.UserExecutionException;

public abstract class NPOHardProblem<CERTIFICATE extends Certificate>
        implements NPHardProblem<CERTIFICATE> {
    private NPOProblem<CERTIFICATE> optimizationProblem;
    private double k;

    public NPOHardProblem(NPOProblem<CERTIFICATE> optimizationProblem, double k) {
        this.optimizationProblem = optimizationProblem;
        this.k = k;
    }

    @Override
    public boolean verify(CERTIFICATE certificate) throws UserExecutionException {
        double opt = optimizationProblem.opt(certificate);
        if (opt <= k) {
            return true;
        } else {
            return false;
        }
    }
}

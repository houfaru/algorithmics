package com.algorithmics.npo.core;

import com.algorithmics.np.core.Certificate;
import com.algorithmics.np.core.NPProblem;
import com.algorithmics.servicesupport.UserExecutionException;

public interface NPOProblem<C extends Certificate> extends NPProblem<C> {
    public double opt(C certificate) throws UserExecutionException;
}

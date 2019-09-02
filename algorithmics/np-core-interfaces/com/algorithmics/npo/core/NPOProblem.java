package com.algorithmics.npo.core;

import com.algorithmics.np.core.Certificate;
import com.algorithmics.np.core.NPProblem;

public interface NPOProblem<C extends Certificate> extends NPProblem<C> {
    public double opt(C certificate);
}

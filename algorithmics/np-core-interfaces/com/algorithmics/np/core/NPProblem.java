package com.algorithmics.np.core;

public interface NPProblem<CERTIFICATE extends Certificate> {
    public boolean verify(CERTIFICATE certificate);
}

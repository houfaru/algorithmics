package com.algorithmics.np.core;

import com.algorithmics.servicesupport.UserExecutionException;

public interface NPProblem<CERTIFICATE extends Certificate> {
    public boolean verify(CERTIFICATE certificate) throws UserExecutionException;
}

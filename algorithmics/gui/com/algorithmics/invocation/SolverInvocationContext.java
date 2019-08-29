package com.algorithmics.invocation;

public interface SolverInvocationContext {
    
    public String getFileLocation();

    public String getInputString();

    public void sendResult();
    
}

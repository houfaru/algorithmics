package com.algorithmics.servicesupport;

public class ExecutionException extends Exception {

    private static final long serialVersionUID = 1L;
    public ExecutionException(Exception source) {
        super(source);
    }
    public ExecutionException(String string) {
        super(string);
    }

}

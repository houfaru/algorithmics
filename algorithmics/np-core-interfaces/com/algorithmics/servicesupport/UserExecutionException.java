package com.algorithmics.servicesupport;

public class UserExecutionException extends Exception {

    private static final long serialVersionUID = 1L;
    public UserExecutionException(Exception source) {
        super(source);
    }
    public UserExecutionException(String string) {
        super(string);
    }

}

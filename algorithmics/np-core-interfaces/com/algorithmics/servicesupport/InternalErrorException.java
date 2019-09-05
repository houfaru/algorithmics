package com.algorithmics.servicesupport;

public class InternalErrorException extends Exception {
    private static final long serialVersionUID = 1L;

    public InternalErrorException(Exception source) {
        super(source);
    }

    public InternalErrorException(String string) {
        super(string);
    }
}

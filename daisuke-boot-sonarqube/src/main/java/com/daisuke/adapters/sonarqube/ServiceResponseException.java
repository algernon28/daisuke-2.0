package com.daisuke.adapters.sonarqube;

public class ServiceResponseException extends RuntimeException {
    private static final long serialVersionUID = 9025892779139262834L;

    public ServiceResponseException() {
	super();
    }

    public ServiceResponseException(String message) {
	super(message);
    }

    public ServiceResponseException(Throwable cause) {
	super(cause);
    }

    public ServiceResponseException(String message, Throwable cause) {
	super(message, cause);
    }

    public ServiceResponseException(String message, Throwable cause, boolean enableSuppression,
	    boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

}

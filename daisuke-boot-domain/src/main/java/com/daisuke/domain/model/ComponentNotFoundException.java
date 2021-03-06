package com.daisuke.domain.model;

import com.daisuke.domain.adapters.SearchException;

public class ComponentNotFoundException extends SearchException {

    private static final long serialVersionUID = -2964994367943592693L;

    public ComponentNotFoundException() {
	super();
    }

    public ComponentNotFoundException(String message) {
	super(message);
    }

    public ComponentNotFoundException(Throwable cause) {
	super(cause);
    }

    public ComponentNotFoundException(String message, Throwable cause) {
	super(message, cause);
    }

    public ComponentNotFoundException(String message, Throwable cause, boolean enableSuppression,
	    boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

}

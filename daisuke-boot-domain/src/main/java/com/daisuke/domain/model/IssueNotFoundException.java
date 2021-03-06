package com.daisuke.domain.model;

import com.daisuke.domain.adapters.SearchException;

public class IssueNotFoundException extends SearchException {

    /**
     * 
     */
    private static final long serialVersionUID = -1412701530464221981L;

    public IssueNotFoundException() {
	super();
    }

    public IssueNotFoundException(String message) {
	super(message);
    }

    public IssueNotFoundException(Throwable cause) {
	super(cause);
    }

    public IssueNotFoundException(String message, Throwable cause) {
	super(message, cause);
    }

    public IssueNotFoundException(String message, Throwable cause, boolean enableSuppression,
	    boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

}

package com.daisuke.adapters.sonarqube;

import com.daisuke.domain.adapters.SearchException;

public class RuleNotFoundException extends SearchException {

    private static final long serialVersionUID = -4650612963138443149L;

    public RuleNotFoundException() {
	super();
    }

    public RuleNotFoundException(String message) {
	super(message);
    }

    public RuleNotFoundException(Throwable cause) {
	super(cause);
    }

    public RuleNotFoundException(String message, Throwable cause) {
	super(message, cause);
    }

    public RuleNotFoundException(String message, Throwable cause, boolean enableSuppression,
	    boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

}

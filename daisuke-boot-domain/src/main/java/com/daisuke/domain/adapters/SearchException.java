package com.daisuke.domain.adapters;

public class SearchException extends Exception {

    private static final long serialVersionUID = 4602399059802845309L;

    public SearchException() {
	super();
    }

    public SearchException(String message) {
	super(message);
    }

    public SearchException(Throwable cause) {
	super(cause);
    }

    public SearchException(String message, Throwable cause) {
	super(message, cause);
    }

    public SearchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

}

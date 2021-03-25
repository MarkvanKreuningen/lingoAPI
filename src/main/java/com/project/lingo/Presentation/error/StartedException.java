package com.project.lingo.Presentation.error;

public class StartedException extends Exception{
    private static final long serialVersionUID = 5861310537366287163L;

    public StartedException() {
        super();
    }

    public StartedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public StartedException(final String message) {
        super(message);
    }

    public StartedException(final Throwable cause) {
        super(cause);
    }
}

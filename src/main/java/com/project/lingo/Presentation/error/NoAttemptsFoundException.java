package com.project.lingo.Presentation.error;

public class NoAttemptsFoundException extends Exception{
    private static final long serialVersionUID = 5861310537366287163L;

    public NoAttemptsFoundException() {
        super();
    }

    public NoAttemptsFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NoAttemptsFoundException(final String message) {
        super(message);
    }

    public NoAttemptsFoundException(final Throwable cause) {
        super(cause);
    }
}


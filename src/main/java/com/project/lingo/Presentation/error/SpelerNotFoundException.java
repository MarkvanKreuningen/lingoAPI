package com.project.lingo.Presentation.error;

public final class SpelerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public SpelerNotFoundException() {
        super();
    }

    public SpelerNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SpelerNotFoundException(final String message) {
        super(message);
    }

    public SpelerNotFoundException(final Throwable cause) {
        super(cause);
    }

}

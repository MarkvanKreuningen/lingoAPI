package com.project.lingo.presentation.error;

public final class SpelerAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public SpelerAlreadyExistException() {
        super();
    }

    public SpelerAlreadyExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SpelerAlreadyExistException(final String message) {
        super(message);
    }

    public SpelerAlreadyExistException(final Throwable cause) {
        super(cause);
    }

}

package com.project.lingo.Presentation.error;

public class GameNotFoundException extends Exception{
    private static final long serialVersionUID = 5861310537366287163L;

    public GameNotFoundException() {
        super();
    }

    public GameNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public GameNotFoundException(final String message) {
        super(message);
    }

    public GameNotFoundException(final Throwable cause) {
        super(cause);
    }
}

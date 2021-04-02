package com.project.lingo.presentation.error;

public class GameOverException extends Exception{
    private static final long serialVersionUID = 5861310537366287163L;

    public GameOverException() {
        super();
    }

    public GameOverException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public GameOverException(final String message) {
        super(message);
    }

    public GameOverException(final Throwable cause) {
        super(cause);
    }
}

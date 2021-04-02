package com.project.lingo.presentation.error;

public class UserHasNoGamesException extends Exception{
    private static final long serialVersionUID = 5861310537366287163L;

    public UserHasNoGamesException() {
        super();
    }

    public UserHasNoGamesException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserHasNoGamesException(final String message) {
        super(message);
    }

    public UserHasNoGamesException(final Throwable cause) {
        super(cause);
    }
}

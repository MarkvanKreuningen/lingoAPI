package com.project.lingo.Presentation.error;

public class NewGameException extends Exception{
    private static final long serialVersionUID = 5861310537366287163L;

    public NewGameException() {
        super();
    }

    public NewGameException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NewGameException(final String message) {
        super(message);
    }

    public NewGameException(final Throwable cause) {
        super(cause);
    }
}

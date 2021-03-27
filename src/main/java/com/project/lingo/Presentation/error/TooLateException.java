package com.project.lingo.Presentation.error;

public class TooLateException extends Exception{
    private static final long serialVersionUID = 5861310537366287163L;

    public TooLateException() {
        super();
    }

    public TooLateException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TooLateException(final String message) {
        super(message);
    }

    public TooLateException(final Throwable cause) {
        super(cause);
    }
}
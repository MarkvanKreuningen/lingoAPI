package com.project.lingo.presentation.error;

public class WordNotValidException extends Exception{
    private static final long serialVersionUID = 5861310537366287163L;

    public WordNotValidException() {
        super();
    }

    public WordNotValidException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public WordNotValidException(final String message) {
        super(message);
    }

    public WordNotValidException(final Throwable cause) {
        super(cause);
    }
}

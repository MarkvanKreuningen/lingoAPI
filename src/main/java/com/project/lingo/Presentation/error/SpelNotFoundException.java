package com.project.lingo.Presentation.error;

public class SpelNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 5861310537366287163L;

    public SpelNotFoundException() {
        super();
    }

    public SpelNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SpelNotFoundException(final String message) {
        super(message);
    }

    public SpelNotFoundException(final Throwable cause) {
        super(cause);
    }
}

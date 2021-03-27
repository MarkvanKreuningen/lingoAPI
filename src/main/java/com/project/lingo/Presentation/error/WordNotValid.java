package com.project.lingo.Presentation.error;

public class WordNotValid extends Exception{
    private static final long serialVersionUID = 5861310537366287163L;

    public WordNotValid() {
        super();
    }

    public WordNotValid(final String message, final Throwable cause) {
        super(message, cause);
    }

    public WordNotValid(final String message) {
        super(message);
    }

    public WordNotValid(final Throwable cause) {
        super(cause);
    }
}

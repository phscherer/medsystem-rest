package br.com.medsystem.exceptions;

public class DoutorJaExisteException extends RuntimeException {
    
    public DoutorJaExisteException() {
    }

    public DoutorJaExisteException(String message) {
        super(message);
    }

    public DoutorJaExisteException(Throwable cause) {
        super(cause);
    }

    public DoutorJaExisteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DoutorJaExisteException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}

package br.com.medsystem.exceptions;

public class ConsultaJaExisteException extends RuntimeException {
    
    public ConsultaJaExisteException() {
    }

    public ConsultaJaExisteException(String message) {
        super(message);
    }

    public ConsultaJaExisteException(Throwable cause) {
        super(cause);
    }

    public ConsultaJaExisteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsultaJaExisteException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}

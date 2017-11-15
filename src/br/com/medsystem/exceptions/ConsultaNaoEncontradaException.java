package br.com.medsystem.exceptions;

public class ConsultaNaoEncontradaException extends RuntimeException {
    
    public ConsultaNaoEncontradaException() {

    }

    public ConsultaNaoEncontradaException(String message) {
        super(message);

    }

    public ConsultaNaoEncontradaException(Throwable cause) {
        super(cause);

    }

    public ConsultaNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);

    }

    //Construtor JDK 1.7
    public ConsultaNaoEncontradaException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }
    
}

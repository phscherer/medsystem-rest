package br.com.medsystem.exceptions;

public class DoutorNaoEncontradoException extends RuntimeException {
    
    public DoutorNaoEncontradoException() {

    }

    public DoutorNaoEncontradoException(String message) {
        super(message);

    }

    public DoutorNaoEncontradoException(Throwable cause) {
        super(cause);

    }

    public DoutorNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);

    }

    //Construtor JDK 1.7
    public DoutorNaoEncontradoException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }
    
}

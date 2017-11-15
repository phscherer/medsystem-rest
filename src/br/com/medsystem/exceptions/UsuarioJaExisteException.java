package br.com.medsystem.exceptions;

public class UsuarioJaExisteException extends RuntimeException {

	public UsuarioJaExisteException() {
	}

	public UsuarioJaExisteException(String message) {
		super(message);
	}

	public UsuarioJaExisteException(Throwable cause) {
		super(cause);
	}

	public UsuarioJaExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioJaExisteException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

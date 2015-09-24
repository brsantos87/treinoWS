package br.com.bssistem.infra.excecoes;


@SuppressWarnings("serial")
public class TRFException extends RuntimeException {
	
	public TRFException() {
		super();
	}

	public TRFException(String message, Throwable cause) {
		super(message, cause);
	}

	public TRFException(String message) {
		super(message);
	}

	public TRFException(Throwable cause) {
		super(cause);
	}
	
}

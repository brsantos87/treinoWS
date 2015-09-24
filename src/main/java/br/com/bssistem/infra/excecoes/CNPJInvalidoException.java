package br.com.bssistem.infra.excecoes;

@SuppressWarnings("serial")
public class CNPJInvalidoException extends TRFException {

	public CNPJInvalidoException() {
		super();
	}

	public CNPJInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public CNPJInvalidoException(String message) {
		super(message);
	}

	public CNPJInvalidoException(Throwable cause) {
		super(cause);
	}
}

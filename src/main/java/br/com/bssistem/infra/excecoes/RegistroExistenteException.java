package br.com.bssistem.infra.excecoes;

@SuppressWarnings("serial")
public class RegistroExistenteException extends TRFException {

	public RegistroExistenteException() {
		super();
	}

	public RegistroExistenteException(String message, Throwable cause) {
		super(message, cause);
	}

	public RegistroExistenteException(String message) {
		super(message);
	}

	public RegistroExistenteException(Throwable cause) {
		super(cause);
	}
	
}

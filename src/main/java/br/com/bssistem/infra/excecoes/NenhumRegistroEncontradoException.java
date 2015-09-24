package br.com.bssistem.infra.excecoes;

@SuppressWarnings("serial")
public class NenhumRegistroEncontradoException extends TRFException {
	
	public NenhumRegistroEncontradoException() {
		super();
	}

	public NenhumRegistroEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public NenhumRegistroEncontradoException(String message) {
		super(message);
	}

	public NenhumRegistroEncontradoException(Throwable cause) {
		super(cause);
	}

}

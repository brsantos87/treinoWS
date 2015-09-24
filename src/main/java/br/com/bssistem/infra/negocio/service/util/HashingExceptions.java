package br.com.bssistem.infra.negocio.service.util;


/**
 * @author Gerv�sio Kayser
 *
 * Classe de excecao responsavel por encapsular as excecoes de baixo nivel geradas
 * no processo de geracao de Hash.
 */
public class HashingExceptions extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HashingExceptions(String msg){
		
		super(msg);
	}
}

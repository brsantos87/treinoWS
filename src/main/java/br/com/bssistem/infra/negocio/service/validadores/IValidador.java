package br.com.bssistem.infra.negocio.service.validadores;

/**
 * @author Anderson Fonseca
 * */

public interface IValidador {

	public void setObjeto(Object objeto);
	
	public abstract void validar();
	
}

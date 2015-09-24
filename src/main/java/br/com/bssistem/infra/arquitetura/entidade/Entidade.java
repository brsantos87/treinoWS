package br.com.bssistem.infra.arquitetura.entidade;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Anderson Fonseca
 * */

public interface Entidade extends Serializable {

	Serializable getIdentificador();
	
	String getDescricao();
	
	
}

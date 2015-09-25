package br.com.bssistem.infra.arquitetura.entidade;

import java.io.Serializable;

public interface Entidade extends Serializable {

	Serializable getIdentificador();
	
	String getDescricao();
	
	
}

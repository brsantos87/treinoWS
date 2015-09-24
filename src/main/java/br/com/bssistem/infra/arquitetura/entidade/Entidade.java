package br.com.bssistem.infra.arquitetura.entidade;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public interface Entidade extends Serializable {

	@JsonIgnoreProperties
	Serializable getIdentificador();
	
	@JsonIgnoreProperties
	String getDescricao();
	
	
}

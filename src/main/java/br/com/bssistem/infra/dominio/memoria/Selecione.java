package br.com.bssistem.infra.dominio.memoria;

import java.io.Serializable;

import br.com.bssistem.infra.arquitetura.entidade.Entidade;

/**
 * @author Anderson Fonseca
 * */

public class Selecione implements Entidade {

	private static final long serialVersionUID = 1L;
	private String desc;

	@Override
	public Serializable getIdentificador() {
		return null;
	}

	@Override
	public String getDescricao() {
		return "Selecione";
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}

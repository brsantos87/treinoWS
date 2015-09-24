package br.com.bssistem.infra.negocio.service.enums;

public enum Operacao {

	INCLUIR(1), EDITAR(2), PESQUISAR(3), VISUALIZAR(4), REMOVER(5);
	
	private Integer value;
	
	private Operacao(Integer val) {
		value = val;
	}
	
	public Integer getValue() {
		return value;
	}
	
}

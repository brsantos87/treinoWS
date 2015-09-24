package br.com.bssistem.infra.negocio.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.ResourceBundle;

import br.com.bssistem.infra.arquitetura.entidade.Entidade;
import br.com.bssistem.infra.negocio.bo.ManutencaoBO;

public abstract class ManutencaoServiceImpl<E extends Entidade, D extends ManutencaoBO<E>>
		implements ManutencaoService<E> {
	
	private ResourceBundle propProjeto = ResourceBundle.getBundle("br.com.bssistem.mensagens_projeto");
	private ResourceBundle prop = ResourceBundle.getBundle("br.com.bssistem.mensagens");

	public abstract ManutencaoBO<E> getManutencaoBO();
	
	public E obter(Serializable identificador) {
		return (E) getManutencaoBO().obter(identificador);
	}

	public void alterar(E entidade) {
		getManutencaoBO().alterar(entidade);
	}

	public void salvar(E entidade) {
		getManutencaoBO().salvar(entidade);
	}

	public void remover(E entidade) {
		getManutencaoBO().remover(entidade);
	}

	public void removerTodos(Collection<E> entidades) {
		getManutencaoBO().removerTodos(entidades);
	}

	public Collection<E> consultar(E entidade) {
		return getManutencaoBO().pesquisar(entidade);
	}

	public Collection<E> consultar() {
		return getManutencaoBO().consultar();
	}
	
	public Collection<E> consultarPorOrderBy(String att){
		return getManutencaoBO().consultarPorOrderBy(att);
	}

	public Collection<E> consultar(E entidade, Integer quantidadeDeRegistros,
			Integer paginaAtual) {
		return getManutencaoBO().consultar(entidade, quantidadeDeRegistros, paginaAtual);
	}

	public ResourceBundle getPropProjeto() {
		return propProjeto;
	}

	public ResourceBundle getProp() {
		return prop;
	}
	
}

package br.com.bssistem.infra.negocio.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.ResourceBundle;

import br.com.bssistem.infra.arquitetura.entidade.Entidade;
import br.com.bssistem.infra.negocio.bo.GenericBO;

public abstract class GenericServiceImpl<E extends Entidade, D extends GenericBO<E>>
		implements GenericService<E> {
	
	private ResourceBundle propProjeto = ResourceBundle.getBundle("br.com.bssistem.mensagens_projeto");
	private ResourceBundle prop = ResourceBundle.getBundle("br.com.bssistem.mensagens");

	public abstract GenericBO<E> getGenericBO();
	
	public E obter(Serializable identificador) {
		return (E) getGenericBO().obter(identificador);
	}

	public void alterar(E entidade) {
		getGenericBO().alterar(entidade);
	}

	public void salvar(E entidade) {
		getGenericBO().salvar(entidade);
	}

	public void remover(E entidade) {
		getGenericBO().remover(entidade);
	}

	public void removerTodos(Collection<E> entidades) {
		getGenericBO().removerTodos(entidades);
	}

	public Collection<E> consultar(E entidade) {
		return getGenericBO().pesquisar(entidade);
	}

	public Collection<E> consultar() {
		return getGenericBO().consultar();
	}
	
	public Collection<E> consultarPorOrderBy(String att){
		return getGenericBO().consultarPorOrderBy(att);
	}

	public Collection<E> consultar(E entidade, Integer quantidadeDeRegistros,
			Integer paginaAtual) {
		return getGenericBO().consultar(entidade, quantidadeDeRegistros, paginaAtual);
	}

	public ResourceBundle getPropProjeto() {
		return propProjeto;
	}

	public ResourceBundle getProp() {
		return prop;
	}
	
}

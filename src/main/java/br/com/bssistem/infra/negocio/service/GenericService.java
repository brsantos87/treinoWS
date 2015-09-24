package br.com.bssistem.infra.negocio.service;

import java.io.Serializable;
import java.util.Collection;

import br.com.bssistem.infra.arquitetura.entidade.Entidade;
import br.com.bssistem.infra.negocio.bo.GenericBO;

public interface GenericService<E extends Entidade> {

	GenericBO<E> getGenericBO();

	E obter(Serializable identificador);

	void alterar(E entidade);

	void salvar(E entidade);

	void remover(E entidade);

	void removerTodos(Collection<E> entidades);

	Collection<E> consultar(E entidade);

	Collection<E> consultar();

	Collection<E> consultarPorOrderBy(String att);

	Collection<E> consultar(E entidade, Integer quantidadeDeRegistros,
			Integer paginaAtual);

}

package br.com.bssistem.infra.arquitetura.integracao;

import java.io.Serializable;
import java.util.Collection;

import br.com.bssistem.infra.arquitetura.entidade.Entidade;

public interface DAO<E extends Entidade> {
	
	E obter(Serializable paramSerializable);

	Serializable inserir(E paramE);

	void alterar(E paramE);

	void salvar(E paramE);
	
	void merge(E entidade);

	void remover(E paramE);

	void removerTodos(Collection<E> paramCollection);
	
	Collection<E> consultar(E paramE);
	
	Collection<E> consultarExistente(E paramE);

	Collection<E> consultar();
	
	Collection<E> consultarPorOrderBy(String att);

	Collection<E> consultar(E paramE, Integer paramInteger1, Integer paramInteger2);

}

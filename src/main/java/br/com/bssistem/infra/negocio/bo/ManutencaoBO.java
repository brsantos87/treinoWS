package br.com.bssistem.infra.negocio.bo;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import br.com.bssistem.infra.arquitetura.entidade.Entidade;
import br.com.bssistem.infra.negocio.service.pesquisa.IObjetoDePesquisa;

/**
 * @author Anderson Fonseca
 * */

public interface ManutencaoBO<E extends Entidade> {

	  @Transactional(readOnly = true)
	  E obter(Serializable identificador);

	  @Transactional
	  void alterar(E entidade);

	  @Transactional
	  void salvar(E entidade);

	  @Transactional
	  void remover(E entidade);

	  @Transactional
	  void removerTodos(Collection<E> entidades);

	  @Transactional(readOnly = true)
	  Collection<E> pesquisar(E entidade);

	  @Transactional(readOnly = true)
	  Collection<E> consultar();
	  
	  @Transactional(readOnly = true)
	  Collection<E> consultarPorOrderBy(String att);

	  @Transactional(readOnly = true)
	  Collection<E> consultar(E entidade, Integer quantidadeDeRegistros,
			Integer paginaAtual);
	  
	  IObjetoDePesquisa definirObjetoDePesquisa();
	  
	  void validar(Object objeto);

	

	
}

package br.com.bssistem.infra.negocio.bo;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import br.com.bssistem.infra.arquitetura.entidade.Entidade;
import br.com.bssistem.infra.arquitetura.integracao.DAO;
import br.com.bssistem.infra.excecoes.RegistroExistenteException;
import br.com.bssistem.infra.negocio.service.pesquisa.IObjetoDePesquisa;
import br.com.bssistem.infra.negocio.service.util.UtilProperties;

public abstract class GenericBOImpl<E extends Entidade, D extends DAO<E>>
		implements GenericBO<E> {
	
	public abstract DAO<E> getDAO();
	
	
	@Transactional(readOnly = true)
	public E obter(Serializable identificador) {
		return (E) getDAO().obter(identificador);
	}

	@Transactional
	public void alterar(E entidade) {
		if (!verificarRegistroExistente(entidade)){
			getDAO().alterar(entidade);	
		}
	}

	@Transactional
	public void salvar(E entidade) throws HibernateException{
			getDAO().salvar(entidade);
	}

	@Transactional
	public void remover(E entidade) {
		getDAO().remover(entidade);
	}

	@Transactional
	public void removerTodos(Collection<E> entidades) {
		getDAO().removerTodos(entidades);
	}

	@Transactional(readOnly = true)
	public Collection<E> pesquisar(E entidade) {
		return getDAO().consultar(entidade);			
	}

	@Transactional(readOnly = true)
	public Collection<E> consultar() {
		return getDAO().consultar();
	}
	
	@Transactional(readOnly = true)
	public Collection<E> consultarPorOrderBy(String att){
		return getDAO().consultarPorOrderBy(att);
	}

	@Transactional(readOnly = true)
	public Collection<E> consultar(E entidade, Integer quantidadeDeRegistros,
			Integer paginaAtual) {
		return getDAO().consultar(entidade, quantidadeDeRegistros, paginaAtual);
	}
	
	@Transactional(readOnly = true)
	private boolean verificarRegistroExistente(E entidade){
		Collection<E> lista = getDAO().consultarExistente(entidade);
		if (lista.size() > 0){
			throw new RegistroExistenteException(UtilProperties.getPropProjeto().getString("register.exist"));
		}
		return false;
	}

	public abstract IObjetoDePesquisa definirObjetoDePesquisa();
	
	public void validar(Object objeto){}
	
}

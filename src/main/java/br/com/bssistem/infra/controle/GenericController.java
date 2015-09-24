package br.com.bssistem.infra.controle;

import java.io.Serializable;
import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.bssistem.infra.arquitetura.entidade.Entidade;
import br.com.bssistem.infra.arquitetura.entidade.GenericCollection;
import br.com.bssistem.infra.negocio.service.GenericService;

public abstract class GenericController<E extends Entidade, MS extends GenericService<E>>
		extends AbstractMenssageController {

	
	public GenericController() {
		super();
	}

	private Logger logger = Logger.getLogger(GenericController.class);
	
	public abstract GenericService<E> getGenericService();
	
	protected Object returnGenericCollection(GenericCollection collection){
		if(getReturnType().equals(ReturnType.JSON))
			return collection.getLista();
		else
			return collection; 
	}
	
	public E obter(Serializable identificador) {
		return (E) getGenericService().obter(identificador);
	}
	
	public void alterar(E entidade) {
		getGenericService().alterar(entidade);
	}

	public void salvar(E entidade) {
		getGenericService().salvar(entidade);
	}

	public void remover(E entidade) {
		getGenericService().remover(entidade);
	}

	public void removerTodos(Collection<E> entidades) {
		getGenericService().removerTodos(entidades);
	}

	public Collection<E> consultar(E entidade) {
		return getGenericService().consultar(entidade);
	}

	public Collection<E> consultar() {
		return getGenericService().consultar();
	}
	
	public Collection<E> consultarPorOrderBy(String att){
		return getGenericService().consultarPorOrderBy(att);
	}

	public Collection<E> consultar(E entidade, Integer quantidadeDeRegistros,
			Integer paginaAtual) {
		return getGenericService().consultar(entidade, quantidadeDeRegistros, paginaAtual);
	}
	
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	

}

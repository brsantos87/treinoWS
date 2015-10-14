package br.com.bssistem.infra.controle;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.bssistem.infra.arquitetura.entidade.Entidade;
import br.com.bssistem.infra.arquitetura.entidade.GenericCollection;
import br.com.bssistem.infra.negocio.service.GenericService;

public abstract class GenericController<E extends Entidade, GS extends GenericService<E>>
		extends AbstractMenssageController {

	public GenericController() {
		super();
	}

	private Logger logger = Logger.getLogger(GenericController.class);
	
	public abstract GenericService<E> getGenericService();
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> findAll() {
		GenericCollection<E> list = new GenericCollection<E>();
		list.setLista(getGenericService().consultar());
		
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<E> findById(@PathVariable("id") String identificador) {
		try{
			return new ResponseEntity<E>(getGenericService().obter(Long.parseLong(identificador)), HttpStatus.OK);
			
		}catch(NumberFormatException nfe){
		    getHeaders().set("ERRO", nfe.getMessage());
			return new ResponseEntity<E>(getHeaders(),HttpStatus.BAD_REQUEST);
		}
	}
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<E> save(E entity) {
		try {
			getGenericService().salvar(entity);
			return new ResponseEntity<E>(HttpStatus.OK);
		} catch (HibernateException hex) {
			return new ResponseEntity<E>(getHeaders(),HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public void update(E entidade) {
		getGenericService().alterar(entidade);
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

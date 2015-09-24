package br.com.bssistem.infra.controle;

import org.apache.log4j.Logger;

import br.com.bssistem.infra.arquitetura.entidade.Entidade;
import br.com.bssistem.infra.negocio.service.ManutencaoService;

public abstract class GenericController<E extends Entidade, MS extends ManutencaoService<E>>
		extends AbstractMenssageController {

	

	private Logger logger = Logger.getLogger(GenericController.class);

	public abstract ManutencaoService<E> getManutencaoService();

	public GenericController() {
		super();
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}


}

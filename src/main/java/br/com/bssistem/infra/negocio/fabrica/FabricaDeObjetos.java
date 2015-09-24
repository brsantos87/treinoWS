package br.com.bssistem.infra.negocio.fabrica;

import org.apache.log4j.Logger;

import br.com.bssistem.infra.arquitetura.entidade.Entidade;
import br.com.bssistem.infra.arquitetura.to.TO;
import br.com.bssistem.infra.dominio.memoria.Selecione;

public class FabricaDeObjetos {

	private Logger logger = Logger.getLogger(FabricaDeObjetos.class);
	
	public FabricaDeObjetos() {}
	
	public Entidade createEntidade(Class<?> clazz) {
		try {
			return (Entidade) clazz.newInstance();
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public TO createTO(Class<?> clazz) {
		try {
			return (TO) clazz.newInstance();
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public Object create(Class<?> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}


	public Selecione createSelecione(){
		return (Selecione) createEntidade(Selecione.class);
	}
}

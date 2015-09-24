package br.com.bssistem.infra.negocio.service.validadores;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * @author Anderson Fonseca
 * */

public abstract class Validador implements IValidador {

	private Logger logger = Logger.getLogger(Validador.class);
	
	private ResourceBundle prop = ResourceBundle.getBundle("br.com.bssistem.mensagens");

	private ResourceBundle propProjeto = ResourceBundle.getBundle("br.com.bssistem.mensagens_projeto");
	
	private Object objeto;
	
	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

	public Logger getLogger() {
		return logger;
	}

	public ResourceBundle getProp() {
		return prop;
	}

	public ResourceBundle getPropProjeto() {
		return propProjeto;
	}
	
	public abstract void validar();
	
	
}

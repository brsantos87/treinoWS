package br.com.bssistem.infra.negocio.service.validadores;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anderson Fonseca
 * */

public class ValidadorFabrica {

	private static ValidadorFabrica instancia;
	private static List<IValidador> validadores;
	
	private Object objeto;
	
	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	
	public static ValidadorFabrica getInstancia(){
		if (instancia == null) {
			instancia = new ValidadorFabrica();
			
		}
		return instancia;
	}
	
	private ValidadorFabrica(){
		if (validadores == null){
			validadores = new ArrayList<IValidador>();
			validadores.add(new ValidadorPeriodo());
			validadores.add(new ValidadorCNPJ());
			validadores.add(new ValidadorCPF());
		}
	}

	public void validar(){
		for (IValidador val : validadores) {
			val.setObjeto(objeto);
			val.validar();
		}
	}
	
	public void adicionarValidador(IValidador validador){
		validadores.add(validador);
	}
	
}

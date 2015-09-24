package br.com.bssistem.treinows.dominio;


import br.com.bssistem.infra.arquitetura.entidade.GenericCollection;
import br.com.bssistem.infra.negocio.fabrica.FabricaDeObjetos;
import br.com.bssistem.treinows.dominio.entidade.Usuario;

public abstract class EntidadeAbsFabrica extends FabricaDeObjetos {
	
	private static EntidadeAbsFabrica instance;
	
	public static EntidadeAbsFabrica getFactory(){
		if (instance == null){
			instance = new EntidadeFabrica();
		}
		return instance;
	} 

	public abstract Usuario createUsuario();
	public abstract GenericCollection createGenericCollection();
	

}

package br.com.bssistem.treinows.dominio;


import br.com.bssistem.infra.negocio.fabrica.FabricaDeObjetos;
import br.com.bssistem.treinows.dominio.to.UsuarioTO;

public abstract class TOAbsFabrica extends FabricaDeObjetos {
	
	private static TOAbsFabrica instance;
	
	public static TOAbsFabrica getFactory(){
		if (instance == null){
			instance = new TOFabrica();
		}
		return instance;
	} 

	public abstract UsuarioTO createUsuarioTO();
	
}

package br.com.bssistem.infra.negocio.service.pesquisa;

import br.com.bssistem.infra.negocio.fabrica.FabricaDeObjetos;

public abstract class ObjetoDePesquisaAbsFabrica extends FabricaDeObjetos {

	private static ObjetoDePesquisaAbsFabrica instance;
	
	public static ObjetoDePesquisaAbsFabrica getFactory(){
		if (instance == null){
			instance = new ObjetoDePesquisaFabrica();
		}
		return instance;
	} 
	
	public abstract IObjetoDePesquisa createObjetoDePesquisa();
	
}

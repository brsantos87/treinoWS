package br.com.bssistem.infra.negocio.service.pesquisa;

public class ObjetoDePesquisaFabrica extends ObjetoDePesquisaAbsFabrica {

	public IObjetoDePesquisa createObjetoDePesquisa(){
		return (IObjetoDePesquisa) create(ObjetoDePesquisa.class);
	}
	
}

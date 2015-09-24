package br.com.bssistem.infra.negocio.service.pesquisa;

public class ObjetoDePesquisa implements IObjetoDePesquisa {

	private String[] campos;

	public String[] getCampos() {
		return campos;
	}

	public void setCampos(String[] campos) {
		this.campos = campos;
	}
	
}

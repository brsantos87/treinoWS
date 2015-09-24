package br.com.bssistem.treinows.dominio;

import br.com.bssistem.treinows.dominio.to.UsuarioTO;


public class TOFabrica extends TOAbsFabrica {
	

	@Override
	public UsuarioTO createUsuarioTO() {
		return (UsuarioTO) createTO(UsuarioTO.class);
	}

	

}

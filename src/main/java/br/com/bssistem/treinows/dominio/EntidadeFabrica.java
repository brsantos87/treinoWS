package br.com.bssistem.treinows.dominio;

import br.com.bssistem.infra.arquitetura.entidade.GenericCollection;
import br.com.bssistem.treinows.dominio.entidade.Usuario;

public class EntidadeFabrica extends EntidadeAbsFabrica {
	

	@Override
	public Usuario createUsuario() {
		return (Usuario) createEntidade(Usuario.class);
	}

	@Override
	public GenericCollection createGenericCollection() {
		return (GenericCollection) createEntidade(GenericCollection.class);
	}


}

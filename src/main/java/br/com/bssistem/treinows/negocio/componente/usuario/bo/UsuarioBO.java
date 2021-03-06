package br.com.bssistem.treinows.negocio.componente.usuario.bo;

import java.util.Collection;

import br.com.bssistem.infra.negocio.bo.GenericBO;
import br.com.bssistem.treinows.dominio.entidade.Usuario;
import br.com.bssistem.treinows.dominio.to.UsuarioTO;

public interface UsuarioBO extends GenericBO<Usuario> {

	
	Usuario obterUsuario(Long codigoFluxus);

	Collection<Usuario> consultar(UsuarioTO usuarioTO, Integer codigoOrgao);
	
	void salvarUsuario(Usuario usuario);
	
}

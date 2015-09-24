package br.com.bssistem.treinows.negocio.service.manterusuario;

import java.util.Collection;

import br.com.bssistem.infra.negocio.service.ManutencaoService;
import br.com.bssistem.treinows.dominio.entidade.Usuario;
import br.com.bssistem.treinows.dominio.to.UsuarioTO;

public interface UsuarioService extends ManutencaoService<Usuario> {
	
	Usuario obterUsuario(Long codigoFluxus);

	Collection<Usuario> consultar(UsuarioTO usuarioTO, Integer codigoOrgao);
	
}

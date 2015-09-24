package br.com.bssistem.treinows.negocio.service.manterusuario;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bssistem.infra.negocio.bo.ManutencaoBO;
import br.com.bssistem.infra.negocio.service.ManutencaoServiceImpl;
import br.com.bssistem.treinows.dominio.entidade.Usuario;
import br.com.bssistem.treinows.dominio.to.UsuarioTO;
import br.com.bssistem.treinows.negocio.componente.usuario.bo.UsuarioBO;

@Service
public class UsuarioServiceImpl 
				 extends ManutencaoServiceImpl<Usuario, UsuarioBO> 
				 implements UsuarioService {

	@Inject private UsuarioBO usuarioBO;
	
	
	@Override
	public ManutencaoBO<Usuario> getManutencaoBO() {
		return usuarioBO;
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario obterUsuario(Long codigoFluxus) {
		return usuarioBO.obterUsuario(codigoFluxus);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Usuario> consultar(UsuarioTO usuarioTO, Integer codigoOrgao) {
		return usuarioBO.consultar(usuarioTO, codigoOrgao);
	}
}

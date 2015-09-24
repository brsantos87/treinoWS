package br.com.bssistem.treinows.negocio.service.manterusuario;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.bssistem.infra.negocio.bo.GenericBO;
import br.com.bssistem.infra.negocio.service.GenericServiceImpl;
import br.com.bssistem.treinows.dominio.entidade.Usuario;
import br.com.bssistem.treinows.negocio.componente.usuario.bo.UsuarioBO;

@Service
public class UsuarioServiceImpl 
				 extends GenericServiceImpl<Usuario, UsuarioBO> 
				 implements UsuarioService {

	@Inject private UsuarioBO usuarioBO;
	
	
	@Override
	public GenericBO<Usuario> getGenericBO() {
		return usuarioBO;
	}

}

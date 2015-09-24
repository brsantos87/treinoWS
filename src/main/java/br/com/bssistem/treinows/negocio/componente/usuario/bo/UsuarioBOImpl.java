package br.com.bssistem.treinows.negocio.componente.usuario.bo;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.bssistem.infra.arquitetura.integracao.DAO;
import br.com.bssistem.infra.negocio.bo.ManutencaoBOImpl;
import br.com.bssistem.infra.negocio.service.pesquisa.IObjetoDePesquisa;
import br.com.bssistem.treinows.dominio.entidade.Usuario;
import br.com.bssistem.treinows.dominio.to.UsuarioTO;
import br.com.bssistem.treinows.negocio.componente.usuario.dao.UsuarioDAO;

@Component
public class UsuarioBOImpl extends ManutencaoBOImpl<Usuario, DAO<Usuario>> implements UsuarioBO {

	@Inject private UsuarioDAO usuarioDAO;
	
	@Override
	public DAO<Usuario> getDAO() {
		return usuarioDAO;
	}

	@Override
	public IObjetoDePesquisa definirObjetoDePesquisa() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public Collection<Usuario> consultar(UsuarioTO usuarioTO, Integer codigoOrgao) {
		return null;
	}


	@Transactional
	public void alterar(Usuario usuario) {
		getDAO().alterar(usuario);
	}

	@Override
	public Usuario obterUsuario(Long codigo) {
		return usuarioDAO.obterUsuario(codigo);
	}

	@Override
	@Transactional
	public void salvarUsuario(Usuario usuario) {
		usuarioDAO.salvarUsuario(usuario);
	}
	
}

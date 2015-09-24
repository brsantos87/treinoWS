package br.com.bssistem.treinows.negocio.componente.usuario.dao;

import java.util.Collection;
import java.util.List;

import br.com.bssistem.infra.arquitetura.integracao.DAO;
import br.com.bssistem.treinows.dominio.entidade.Usuario;
import br.com.bssistem.treinows.dominio.to.UsuarioTO;

public interface UsuarioDAO extends DAO<Usuario> {

	Usuario obterUsuarioLogin(String login);

	
	List<Usuario> CPFExistente(String cpf);
	
	Collection<Usuario> consultar(UsuarioTO usuarioTO);
	
	Usuario obterUsuario(Long codigoFluxus);
	
	void salvarUsuario(Usuario usuario);
}

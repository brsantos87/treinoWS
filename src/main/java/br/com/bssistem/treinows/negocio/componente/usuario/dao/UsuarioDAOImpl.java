package br.com.bssistem.treinows.negocio.componente.usuario.dao;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.bssistem.infra.hibernate.dao.HibernateDAOAbstrato;
import br.com.bssistem.infra.negocio.service.util.Util;
import br.com.bssistem.treinows.dominio.entidade.Usuario;
import br.com.bssistem.treinows.dominio.to.UsuarioTO;

@Repository
public class UsuarioDAOImpl extends HibernateDAOAbstrato<Usuario> implements UsuarioDAO {
	
	@Resource(name = "factory")
	public void bindSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario obterUsuarioLogin(String login) {
		Usuario resultado = null;
		Criteria criteria = novoCriteria();
		if (login != null && !login.isEmpty()) {
			criteria.add(Restrictions.eq("login", login));
		}
		resultado =  consultarEntidade(criteria);

		return resultado;
	}


	@Override
	@Transactional(readOnly=true)
	public List<Usuario> CPFExistente(String cpf) {
		List<Usuario> resultado = null;
		Criteria criteria = novoCriteria();
		criteria.add(Restrictions.eq("cpf", cpf));
		resultado = (List<Usuario>) consultar(criteria);

		return resultado;
	}
	

	@Override
	public void remover(Usuario usuario) {
		getHibernateTemplate().delete(usuario);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Usuario> consultar(UsuarioTO usuarioTO) {
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario obterUsuario(Long id) {
		Criteria criteria = novoCriteria();
		if ( !Util.isNullOrEmpty(id) ){
			criteria.add(Restrictions.eq("id", id));
			return (Usuario) criteria.uniqueResult();
		}
		return null;
		
	}

	@Override
	public void salvarUsuario(Usuario usuario) {
		salvar(usuario);		
	}
	
	

}

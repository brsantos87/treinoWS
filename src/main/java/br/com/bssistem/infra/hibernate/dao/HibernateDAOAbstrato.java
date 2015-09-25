package br.com.bssistem.infra.hibernate.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import br.com.bssistem.infra.arquitetura.entidade.Entidade;
import br.com.bssistem.infra.arquitetura.integracao.DAO;
import br.com.bssistem.infra.arquitetura.integracao.PaginacaoConsultaHolder;

public abstract class HibernateDAOAbstrato<T extends Entidade> extends HibernateDaoSupport implements DAO<T> {
	
	public void alterar(T entidade) {
		getHibernateTemplate().update(entidade);
	}

	public Collection<T> consultar() {
		Criteria criteria = novoCriteria();
		return consultar(criteria);
	}
	
	public Collection<T> consultarPorOrderBy(String att) {
		Criteria criteria = novoCriteria();
		criteria.addOrder(Order.asc(att));
		return consultar(criteria);
	}
	
	

	public Collection<T> consultar(T entidade) {
		Collection<T> resultado = null;

		Criteria criteria = novoCriteria();
		Example example = Example.create(entidade);
		example.enableLike(MatchMode.ANYWHERE);
		example.excludeZeroes();
		criteria.add(example);
		resultado = consultar(criteria);

		return resultado;
	}
	
	public Collection<T> consultarExistente(T entidade) {
		Collection<T> resultado = null;

		Criteria criteria = novoCriteria();
		Example example = Example.create(entidade);
		example.enableLike(MatchMode.EXACT);
		example.excludeZeroes();
		criteria.add(example);
		resultado = consultar(criteria);

		return resultado;
	}

	@SuppressWarnings("unchecked")
	public Collection<T> consultar(T entidade, Integer quantidadeDeRegistros,
			Integer paginaAtual) {
		Collection<T> consulta = new ArrayList<T>();
		PaginadorList<T> entidades = new PaginadorList<T>();

		PaginacaoConsultaHolder.setLimiteRegistro(quantidadeDeRegistros);
		PaginacaoConsultaHolder.setNumeroPagina(paginaAtual);

		consulta = consultar(entidade);

		int totalRegistros = PaginacaoConsultaHolder.getTotalRegistros().intValue();
		entidades.setRegistrosPorPagina(quantidadeDeRegistros.intValue());
		entidades.setSize(totalRegistros);

		entidades.addAll(consulta);

		PaginacaoConsultaHolder.setLimiteRegistro(null);
		PaginacaoConsultaHolder.setNumeroPagina(null);

		return entidades;
	}

	public Serializable inserir(T entidade) {
		Serializable resultado = null;
		resultado = getHibernateTemplate().save(entidade);
		return resultado;
	}

	@SuppressWarnings("unchecked")
	public T obter(Serializable pk) {
		Entidade resultado = null;
		Class<?> tipo = getTipoDaEntidade();
		resultado = (Entidade) getHibernateTemplate().get(tipo, pk);

		return (T) resultado;
	}

	public void remover(Entidade entidade) {
		getHibernateTemplate().delete(entidade);	
	}

	public void removerTodos(Collection<T> entidades) {
		for (Entidade entidade : entidades) {
			remover(entidade);
		}
			
	}
	
	public void salvar(T entidade) throws HibernateException{
		try {
			
			getHibernateTemplate().saveOrUpdate(entidade);
		} catch (StaleStateException sse) {
			// TODO: Implementar Log
			sse.printStackTrace();
			throw new HibernateException(sse.getCause());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void merge(T entidade) {
		getHibernateTemplate().merge(entidade);
	}

//	@Resource(name = "mySessionFactory")
//	protected void bindSessionFactory(SessionFactory sessionFactory) {
//		setSessionFactory(sessionFactory);
//	}

	protected void carregarEntidadePersistente(T entidade) {
		Serializable pk = entidade.getIdentificador();
		getHibernateTemplate().load(entidade, pk);
	}

	protected boolean isPersistente(T entidade) {
		return currentSession().contains(entidade);
	}

	@SuppressWarnings("unchecked")
	protected Collection<T> consultar(Criteria criteria) {
		//registrarAcaoDeConsulta();
		//configurarPaginacao(criteria);
		Collection<T> colecao = criteria.list();
		//removerAcaoSolicitada();
		return colecao;
	}
	
	@SuppressWarnings("unchecked")
	protected Collection<T> consultarPorOrderBy(Criteria criteria) {
		//registrarAcaoDeConsulta();
		//configurarPaginacao(criteria);
		Collection<T> colecao = criteria.list();
		//removerAcaoSolicitada();
		return colecao;
	}
	
	@SuppressWarnings("unchecked")
	protected T consultarEntidade(Criteria criteria){
		T entidade = (T) criteria.uniqueResult();
		return entidade;
	}
	
	/**
	 * Configura a paginacao para o criterio informado.<br/> As configuracoes
	 * de paginacao sao definidas no PaginacaoHolder.
	 * 
	 * @param criteria Criterio de consulta.
	 * @see PaginacaoConsultaHolder
	 */
	@SuppressWarnings("boxing")
	protected void configurarPaginacao(Criteria criteria) {
		Integer pagina = PaginacaoConsultaHolder.getNumeroPagina();
		Integer regsPag = PaginacaoConsultaHolder.getLimiteRegistro();

		//if (isReferencia(criteria) && !isZero(regsPag)) {
			Integer total = getQuantidadeTotalDeRegistros(criteria);

			if ((total.intValue() > regsPag)) {
				if (pagina == 0)
					pagina = 1;
				int inicio = (pagina * regsPag) - regsPag;
				criteria.setFirstResult(inicio);
				criteria.setMaxResults(regsPag);
			}

			PaginacaoConsultaHolder.setTotalRegistros(total);
		//}
	}
	
	/**
	 * Retorna a quantidade total de registros da consulta.
	 * 
	 * @param criteria Criterio da consulta.
	 * @return quantidade total de registros da consulta.
	 */
	@SuppressWarnings({"boxing", "unchecked"})
	protected Integer getQuantidadeTotalDeRegistros(Criteria criteria) {
		
		Integer resultado = new Integer(0);
		
			ResultTransformer rt = null;
			if (criteria instanceof CriteriaImpl) {

				rt = ((CriteriaImpl) criteria).getResultTransformer();
				
				//removendo os orders para evitar erro na consulta com count
				List<CriteriaImpl.OrderEntry> orders = new ArrayList<CriteriaImpl.OrderEntry>();
				Iterator<CriteriaImpl.OrderEntry> iterator = 
					((CriteriaImpl) criteria).iterateOrderings();
				while (iterator.hasNext()) {
					orders.add(iterator.next());
					iterator.remove();
				}
				
				//executando o count
				criteria.setProjection(Projections.rowCount());
				resultado = (Integer) criteria.uniqueResult();
				criteria.setProjection(null);
				
				//adicionando os orders no criteria novamente.
				for (CriteriaImpl.OrderEntry order : orders) {
					criteria.addOrder(order.getOrder());
				}
				//if (isReferencia(rt)) {
					criteria.setResultTransformer(rt);
				//}
			} else {
				resultado = getQuantidadeTotalDeRegistros(criteria.scroll());
			}

		return resultado;
	}
	
	/**
	 * Retorna a quantidade total de registros da consulta.
	 * 
	 * @param scrollable Criterio da consulta.
	 * @return quantidade total de registros da consulta.
	 */
	protected Integer getQuantidadeTotalDeRegistros(ScrollableResults scrollable) {
		
		Integer resultado = new Integer(0);
		//if (isReferencia(scrollable)) {
			scrollable.last();
			int rowNumber = scrollable.getRowNumber();
			resultado = new Integer(rowNumber + 1);
		//}
		return resultado;
	}
	
	protected int executar(Query query) {
		int resultado = 0;
		resultado = query.executeUpdate();
		return resultado;
	}

	@SuppressWarnings("unchecked")
	protected T obter(T entidade) {
		Entidade resultado = null;
		resultado = obter(entidade.getIdentificador());
		return (T) resultado;
	}

	protected T obter(Criteria criteria) {
		Collection<T> colecao = consultar(criteria);
		return (T) colecao.iterator().next();
	}

	@SuppressWarnings("unchecked")
	protected T obter(Query query) {
		Entidade resultado = (Entidade) query.uniqueResult();
		return (T) resultado;
	}

	protected ClassMetadata getClassMetadata() {
		Class<?> classe = getTipoDaEntidade();
		return getSessionFactory().getClassMetadata(classe);
	}

	protected Query getQuery(String identificador) {
		return currentSession().getNamedQuery(identificador);
	}

	protected SQLQuery getSQLQuery(String identificador) {
		return ((SQLQuery) currentSession().getNamedQuery(identificador));
	}

	@SuppressWarnings("unchecked")
	private Class<T> getTipoDaEntidade() {
		Class<T> classe = null;

		if (this instanceof HibernateDAOAbstrato) {
			Type[] type = ((ParameterizedType) super.getClass()
					.getGenericSuperclass()).getActualTypeArguments();
			classe = (Class<T>) type[0];
		}
		return classe;
	}

	protected Criteria novoCriteria() {
		Class<?> tipo = getTipoDaEntidade();
		return currentSession().createCriteria(tipo);
	}

	protected Criterion novoCriterioBetween(String propriedade, Object arg0,
			Object arg1) {
		return Restrictions.between(propriedade, arg0, arg1);
	}

	protected Criterion novoCriterioEQ(String propriedade, Object arg0) {
		return Restrictions.eq(propriedade, arg0);
	}

	protected Criterion novoCriterioEQIgnoreCase(String propriedade, Object arg0) {
		SimpleExpression se = Restrictions.eq(propriedade, arg0);
		se.ignoreCase();
		return se;
	}

	protected Criterion novoCriterioLike(String propriedade, String arg0) {
		SimpleExpression se = null;
		se = Restrictions.like(propriedade, arg0, MatchMode.ANYWHERE);
		se.ignoreCase();
		return se;
	}

	protected Criterion novoCriterioLikeDireita(String propriedade, String arg0) {
		SimpleExpression se = null;
		se = Restrictions.like(propriedade, arg0, MatchMode.START);
		se.ignoreCase();
		return se;
	}

	protected Criterion novoCriterioNE(String propriedade, Object arg0) {
		return Restrictions.ne(propriedade, arg0);
	}

	protected SQLQuery novoSQLQuery(String sql) {
		return currentSession().createSQLQuery(sql);
	}
//
//	@Resource(name = "mySessionFactory")
//	protected void bindSessionFactory(SessionFactory sessionFactory) {
//		setSessionFactory(sessionFactory);
//	}
	public abstract void bindSessionFactory(SessionFactory sessionFactory);
	
	
	

}

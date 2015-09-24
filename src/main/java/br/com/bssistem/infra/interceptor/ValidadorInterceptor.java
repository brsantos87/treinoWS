package br.com.bssistem.infra.interceptor;


import java.lang.reflect.Method;
import java.util.Collection;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import br.com.bssistem.infra.excecoes.NenhumRegistroEncontradoException;
import br.com.bssistem.infra.excecoes.PesquisaRegistroException;
import br.com.bssistem.infra.negocio.bo.GenericBO;
import br.com.bssistem.infra.negocio.bo.GenericBOImpl;
import br.com.bssistem.infra.negocio.service.pesquisa.IObjetoDePesquisa;
import br.com.bssistem.infra.negocio.service.util.Util;
import br.com.bssistem.infra.negocio.service.validadores.IValidador;
import br.com.bssistem.infra.negocio.service.validadores.ValidadorCamposObrigatorios;
import br.com.bssistem.infra.negocio.service.validadores.ValidadorFabrica;

/**
 * @author Anderson Fonseca
 * */
@Aspect
public class ValidadorInterceptor {

	private Logger logger = Logger.getLogger(ValidadorInterceptor.class);
	private static ResourceBundle prop = ResourceBundle.getBundle("br.com.bssistem.mensagens");
	private static ResourceBundle propProjeto = ResourceBundle.getBundle("br.com.bssistem.mensagens_projeto");

	
	@Pointcut("execution(* br.com.bssistem.infra.negocio.bo.GenericBOImpl.salvar(..)))")
	private void notNullInserir() { }
	
	@Pointcut("execution(* br.com.bssistem.infra.negocio.bo.GenericBOImpl.alterar(..)))")
	private void notNullAlterar() { }
	
	@Pointcut("execution(* *.pesquisar(..)))")
	private void validarPesquisa() { }
	
	@Pointcut("execution(* *.validar(..)))")
	private void validarObjeto() { }

	
	@Before("validarObjeto()")
	public void validaObrigatorios(JoinPoint pjp) {
		
		Object objetoParam = pjp.getArgs()[0];
		
		IValidador camposObrigatorios = new ValidadorCamposObrigatorios();
		camposObrigatorios.setObjeto(objetoParam);
		camposObrigatorios.validar();

		return;
	}
	
	@Before("validarObjeto() or validarPesquisa()")
	public void validarObjetos(JoinPoint pjp){

		Object objetoParam = pjp.getArgs()[0];
		
		ValidadorFabrica validadorFabrica = ValidadorFabrica.getInstancia();
		validadorFabrica.setObjeto(objetoParam);
		validadorFabrica.validar();
		
		return;
	}
	
	@Before("validarPesquisa()")
	public void validarPesquisaParaPeloMenosUmCampoInformado(JoinPoint pjp){
		boolean resultado = false;

		if (!(pjp.getThis() instanceof GenericBOImpl)){
			return;
		}
		
		GenericBO<?> manutencaoBO = (GenericBOImpl<?,?>) pjp.getThis();
		Object objeto = pjp.getArgs()[0];
		
		IObjetoDePesquisa objetoDePesquisa = manutencaoBO.definirObjetoDePesquisa();
		
		StringBuilder builder = new StringBuilder();
		
		if (objetoDePesquisa == null){
			return;
		}
		
		builder.append(prop.getString("MN023") + "<br/>");
		builder.append("<ul>");
		
		for (String str:objetoDePesquisa.getCampos()){
			try {
				builder.append("<li>" + propProjeto.getString(str) + "</li>");

				Method metodo = objeto.getClass().getMethod("get" + Util.toUppercase(str), null);
				
				Object obj = metodo.invoke(objeto, null);
				if (Util.getMethodReturnType(metodo).isAssignableFrom(String.class)){
					obj = Util.verifyString(obj);
				}
				if (obj != null){
					resultado = true;
					break;
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		
		builder.append("</ul>");
		
		if (!resultado && objetoDePesquisa != null && objetoDePesquisa.getCampos().length > 0){
			throw new PesquisaRegistroException(builder.toString());
		}
		
		return;
	}
	
	@AfterReturning(pointcut="validarPesquisa()", returning="retVal")
	public void verificarRetornoPesquisado(Object retVal){
		if ((retVal instanceof Collection) && ((Collection<?>) retVal).size() == 0){
			throw new NenhumRegistroEncontradoException(prop.getString("MN002"));
		}
	}
}

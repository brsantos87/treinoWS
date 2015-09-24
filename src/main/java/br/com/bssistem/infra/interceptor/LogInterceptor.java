package br.com.bssistem.infra.interceptor;


import java.util.ResourceBundle;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Anderson Fonseca
 * */
@Aspect
public class LogInterceptor {

	private ResourceBundle prop = ResourceBundle.getBundle("br.com.bssistem.mensagens");
	
	private Logger logger = Logger.getLogger(LogInterceptor.class);
	
	@SuppressWarnings("unused")
	@Pointcut("execution(* br.com.bssistem..*.*(..)) and !within(* br.com.bssistem.projeto.dominio.entidade.*(..))")
	private void logDescriptor() { }
	
	@Before("br.com.bssistem.infra.interceptor.LogInterceptor.logDescriptor()")
	public void iniciando(JoinPoint pjp) {
		logger.log(Level.INFO, "Iniciado metodo..."
				+ pjp.getStaticPart().getSignature());
	}
	
	@After("br.com.bssistem.infra.interceptor.LogInterceptor.logDescriptor()")
	public void finalizando(JoinPoint pjp) {
		logger.log(Level.INFO, "Finalizando metodo..."
				+ pjp.getStaticPart().getSignature());
	}
	
	@AfterThrowing(pointcut = "br.com.bssistem.infra.interceptor.LogInterceptor.logDescriptor()", throwing = "ex")
    public void doRecoveryActions(Throwable ex) {
		
		logger.error(ex);
		return;
	}

}

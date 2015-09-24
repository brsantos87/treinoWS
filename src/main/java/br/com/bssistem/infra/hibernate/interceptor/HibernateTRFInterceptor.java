package br.com.bssistem.infra.hibernate.interceptor;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.stereotype.Component;

/**
 * @author Anderson Fonseca
 * */

@SuppressWarnings("serial")
@Component
public class HibernateTRFInterceptor extends EmptyInterceptor {


	public String getEntityName(Object arg0) throws CallbackException {
		Class<?> targetClass = AopProxyUtils.ultimateTargetClass(arg0);
		String name = targetClass.getName();
		return name;
	}

}

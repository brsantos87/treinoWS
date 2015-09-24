package br.com.bssistem.teste;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationTestFactoryUtil {

	private ClassPathXmlApplicationContext context;
	private BeanFactory beanFactory;	
	private static ApplicationTestFactoryUtil instance;
	
	private ApplicationTestFactoryUtil(){
		context = new ClassPathXmlApplicationContext("mvc-dispatcher-servlet.xml");
		beanFactory = context.getBeanFactory();
	}
	
	public static ApplicationTestFactoryUtil obterInstancia(){
		if (instance == null) {
			instance = new ApplicationTestFactoryUtil();
		}
		return instance;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}
	
}

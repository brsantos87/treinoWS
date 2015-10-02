package br.com.bssistem.infra.negocio.service.util;

import java.util.ResourceBundle;

public class UtilProperties {

	private static final ResourceBundle propSystemConfig = ResourceBundle.getBundle("system-config");
	private static final ResourceBundle propProjeto = ResourceBundle.getBundle(getPropSystemConfig().getString("project.package")+".mensagens_projeto");
	private static final ResourceBundle prop = ResourceBundle.getBundle(getPropSystemConfig().getString("project.package")+".mensagens");
	
	
	public static ResourceBundle getPropSystemConfig() {
		return propSystemConfig;
	}
	public static ResourceBundle getPropProjeto() {
		return propProjeto;
	}
	public static ResourceBundle getProp() {
		return prop;
	}
	
	
	
}

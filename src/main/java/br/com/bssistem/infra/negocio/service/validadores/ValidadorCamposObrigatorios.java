package br.com.bssistem.infra.negocio.service.validadores;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.bssistem.infra.annotation.CampoNotNull;
import br.com.bssistem.infra.negocio.service.util.Util;
import br.com.bssistem.treinows.dominio.tipo.StatusEnum;

public class ValidadorCamposObrigatorios extends Validador {
	
	@Override
	public void validar() {
		
		Object entidade = getObjeto();
		Field[] fields = entidade.getClass().getDeclaredFields();
		StringBuilder builder = new StringBuilder();
		ResourceBundle prop = ResourceBundle.getBundle("br.com.bssistem.mensagens");
		ResourceBundle propProjeto = ResourceBundle.getBundle("br.com.bssistem.mensagens_projeto");
		List<String> requiredFields = new ArrayList<String>();
		
		for (Field fld : fields) {
			validarCampos(fld, requiredFields, entidade, propProjeto);
		}
		
		
	}

	
	private void validarCampos(Field fld, List<String> requiredFields, Object entidade, ResourceBundle propProjeto) {
		if (fld.isAnnotationPresent(Valid.class)){
			try {
				Method metodo = Util.getMetodo(entidade, fld.getName());
				Object obj = metodo.invoke(entidade, null);
		
				for (Field field : fld.getType().getDeclaredFields()) {
					validarCampos(field, requiredFields, obj, propProjeto);
				}
			} catch (Exception e) {
				getLogger().error(e.getMessage());
			}
		} else if (fld.isAnnotationPresent(NotNull.class) || (fld.isAnnotationPresent(CampoNotNull.class))){
			try {
				Method metodo = Util.getMetodo(entidade, fld.getName());
				Object obj = metodo.invoke(entidade, null);
				
				if(obj instanceof StatusEnum && ((StatusEnum) obj).equals(StatusEnum.SELECIONE)){
					requiredFields.add(propProjeto.getString(fld.getName()));
				}
				
				if (Util.getMethodReturnType(metodo).isAssignableFrom(String.class)){
					obj = Util.verifyString(obj);
				}
				
				if (obj == null){
					fld.setAccessible(true);
					fld.set(entidade, null);
					requiredFields.add(propProjeto.getString(fld.getName()));
				}
			} catch (Exception e) {
				getLogger().error(e.getMessage());
			}
		}
	}
}

package br.com.bssistem.infra.negocio.service.validadores;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Date;

import br.com.bssistem.infra.annotation.Periodo;
import br.com.bssistem.infra.excecoes.PeriodoInvalidoException;
import br.com.bssistem.infra.negocio.service.util.Util;

@SuppressWarnings("cast")
public class ValidadorDependente extends Validador {

	public void validar(){
		
		Object objetoParam = getObjeto();
		Field[] fields = objetoParam.getClass().getDeclaredFields();
		boolean periodoInvalido = false;
		String nomeCampoDataIni = null;
		String nomeCampoDataFim = null;
		
		for (Field fld : fields) {
			if (fld.isAnnotationPresent(Periodo.class)){
				try {
					Method metodo = Util.getMetodo(objetoParam, fld.getName());
					Date obj = (Date) metodo.invoke(objetoParam, null);
					
					if (obj == null){
						continue;
					}
					
					Periodo p = fld.getAnnotation(Periodo.class);
					
					Method metodo1 = null;
					Date obj1 = null;
					
					if (p.menorQue().trim().length() > 0){
						metodo1 = objetoParam.getClass().getMethod("get" + Util.toUppercase(p.menorQue()), null);
						obj1 = (Date) metodo1.invoke(objetoParam, null);
						
						if (obj1 != null && obj.compareTo(obj1) >= 0){
							periodoInvalido = true;
							nomeCampoDataIni = getPropProjeto().getString(fld.getName());
							nomeCampoDataFim = getPropProjeto().getString(p.menorQue());
							break;
						}
					}
					
					if (p.maiorQue().trim().length() > 0){
						metodo1 = objetoParam.getClass().getMethod("get" + Util.toUppercase(p.maiorQue()), null);
						obj1 = (Date) metodo1.invoke(objetoParam, null);
						
						if (obj1 != null && obj.compareTo(obj1) <= 0){
							periodoInvalido = true;
							nomeCampoDataIni = getPropProjeto().getString(fld.getName());
							nomeCampoDataFim = getPropProjeto().getString(p.menorQue());
							break;
						}
					}
					
				} catch (Exception e) {
					getLogger().error(e.getMessage());
				}
			}
		}
		
		if (periodoInvalido){
			MessageFormat mf = new MessageFormat(getProp().getString("MN025"));
			String msg =  mf.format(new Object[]{nomeCampoDataIni, nomeCampoDataFim}, new StringBuffer(), null).toString();
			throw new PeriodoInvalidoException(msg);
		}
	}
	
}
